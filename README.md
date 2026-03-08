# Back_library – Guía rápida de configuración y despliegue (terminal)

## Requisitos
- Java 17
- Git
- Acceso a GitHub Packages con un PAT (scopes: `write:packages`, `read:packages` y si el repo es privado: `repo`).

## 1) Configuración de Maven embebido (.mvn/)
> Estos archivos **NO deben versionarse**.

```bash
# Si no existe el wrapper:
mvn -N wrapper:wrapper || mvn -N io.takari:maven:wrapper

# Crear carpeta de configuración local
mkdir -p .mvn

# 1.1 settings embebido con tu usuario/token
cat > .mvn/settings.xml <<'XML'
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>ADMIN-BO-TECH</username>
      <password>__TU_PAT_AQUI__</password>
    </server>
  </servers>
</settings>
XML

# 1.2 forzar a Maven a usar ese settings (dos líneas EXACTAS)
printf -- "%s\n%s\n" "-s" ".mvn/settings.xml" > .mvn/maven.config

# 1.3 ignorar estos archivos locales
printf "%s\n%s\n" ".mvn/settings.xml" ".mvn/maven.config" >> .gitignore

# 1.4 verificación
./mvnw help:effective-settings -Doutput=.mvn/effective.xml
grep -A2 "<id>github</id>" .mvn/effective.xml
```

---

## 2) POM mínimo para publicar a GitHub Packages
Asegúrate de que el `pom.xml` tenga este bloque (el `<id>` debe coincidir con el de `settings.xml`):

```xml
<distributionManagement>
  <repository>
    <id>github</id>
    <name>GitHub Packages ADMIN-BO-TECH/back_library</name>
    <url>https://maven.pkg.github.com/ADMIN-BO-TECH/back_library</url>
  </repository>
  <snapshotRepository>
    <id>github</id>
    <name>GitHub Packages ADMIN-BO-TECH/back_library (snapshots)</name>
    <url>https://maven.pkg.github.com/ADMIN-BO-TECH/back_library</url>
  </snapshotRepository>
</distributionManagement>
```

---

## 3) Publicar versiones desde terminal

### 3.1 Publicar **SNAPSHOT** (integración rápida)
```bash
# (opcional) fijar la versión snapshot
./mvnw -q versions:set -DnewVersion=0.2.0-SNAPSHOT -DgenerateBackupPoms=false

# publicar
./mvnw -DskipTests clean deploy
```

### 3.2 Publicar **RELEASE** (producción)
```bash
#0) verificar
./mvnw -U clean verify

# 1) fijar versión estable
./mvnw -q versions:set -DnewVersion=0.2.0 -DgenerateBackupPoms=false

# 2) publicar el release
./mvnw -DskipTests clean deploy

# 3) tag y push
git commit -am "chore(release): 0.2.0"
git tag v0.2.0
git push && git push --tags

# 4) volver a snapshot para continuar desarrollo
./mvnw -q versions:set -DnewVersion=0.2.1-SNAPSHOT -DgenerateBackupPoms=false
git commit -am "chore: bump to 0.2.1-SNAPSHOT"
git push
```

---

## 4) Scripts opcionales (recomendado)
Crea una carpeta `scripts/` con atajos para snapshot/release.

```bash
mkdir -p scripts
```

**`scripts/snapshot.sh`**
```bash
#!/usr/bin/env bash
set -euo pipefail

if [[ $# -lt 1 ]]; then
  echo "Uso: $0 <version-SNAPSHOT>    ej: $0 0.2.0-SNAPSHOT"
  exit 1
fi

SNAPSHOT_VERSION="$1"
ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

if [[ ! -f ".mvn/settings.xml" ]]; then
  echo "ERROR: .mvn/settings.xml no existe. Sigue la guía (Sección 1)."
  exit 1
fi

if [[ ! -x "./mvnw" ]]; then
  mvn -N wrapper:wrapper || mvn -N io.takari:maven:wrapper
fi

./mvnw -q versions:set -DnewVersion="${SNAPSHOT_VERSION}" -DgenerateBackupPoms=false
./mvnw -DskipTests clean deploy

echo "✓ SNAPSHOT publicado: ${SNAPSHOT_VERSION}"
```

**`scripts/release.sh`**
```bash
#!/usr/bin/env bash
set -euo pipefail

if [[ $# -lt 1 ]]; then
  echo "Uso: $0 <release-version> [next-snapshot]"
  echo "Ejemplos:"
  echo "  $0 0.2.0 0.2.1-SNAPSHOT"
  echo "  $0 0.2.0"
  exit 1
fi

RELEASE_VERSION="$1"
NEXT_SNAPSHOT="${2:-}"

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

if ! git diff --quiet || ! git diff --cached --quiet; then
  echo "ERROR: Hay cambios sin commit."
  exit 1
fi

if [[ ! -f ".mvn/settings.xml" ]]; then
  echo "ERROR: .mvn/settings.xml no existe. Sigue la guía (Sección 1)."
  exit 1
fi

if [[ ! -x "./mvnw" ]]; then
  mvn -N wrapper:wrapper || mvn -N io.takari:maven:wrapper
fi

./mvnw -q versions:set -DnewVersion="${RELEASE_VERSION}" -DgenerateBackupPoms=false
./mvnw -DskipTests clean deploy

git commit -am "chore(release): ${RELEASE_VERSION}"
git tag "v${RELEASE_VERSION}"
git push && git push --tags

if [[ -z "${NEXT_SNAPSHOT}" ]]; then
  IFS='.' read -r MA MI PA <<<"${RELEASE_VERSION}"
  PA=$((PA+1))
  NEXT_SNAPSHOT="${MA}.${MI}.${PA}-SNAPSHOT"
fi

./mvnw -q versions:set -DnewVersion="${NEXT_SNAPSHOT}" -DgenerateBackupPoms=false
git commit -am "chore: bump to ${NEXT_SNAPSHOT}"
git push

echo "✓ Release ${RELEASE_VERSION} publicado."
echo "✓ Bump a ${NEXT_SNAPSHOT} listo."
```

Dar permisos:
```bash
chmod +x scripts/*.sh
```

Usos:
```bash
scripts/snapshot.sh 0.2.0-SNAPSHOT
scripts/release.sh 0.2.0
scripts/release.sh 0.2.0 0.2.1-SNAPSHOT
```

---

## 5) Errores comunes y tips

- **`The specified user settings file does not exist: …/ .mvn/settings.xml`**  
  Tu `.mvn/maven.config` debe tener **dos líneas** exactas, sin espacios delante:
  ```
  -s
  .mvn/settings.xml
  ```
  Verifícalo:
  ```bash
  nl -ba .mvn/maven.config
  ```

- **401/403 en `deploy`**  
  Revisa scopes del PAT, usuario correcto en `settings.xml`, y que `<id>github</id>` coincida con `distributionManagement`.

- **422 “artifact already exists” (release)**  
  Los releases son inmutables: publica una nueva versión `X.Y.Z`.

- **Ver settings efectivos**
  ```bash
  ./mvnw help:effective-settings -Doutput=.mvn/effective.xml
  grep -A2 "<id>github</id>" .mvn/effective.xml
  ```

---

## 6) Consumir la librería en microservicios
En el `pom.xml` del **consumidor**:

```xml
<repositories>
  <repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/ADMIN-BO-TECH/back_library</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>co.com.botech</groupId>
    <artifactId>botech-back-library</artifactId>
    <version>0.2.0</version> <!-- o un SNAPSHOT -->
  </dependency>
</dependencies>
```

El desarrollador debe tener un `settings.xml` (global o embebido) con el `<server id="github">` apuntando a su usuario y PAT.

---

## 7) Estrategia de versionamiento (resumen)
- **Snapshot**: `<version>X.Y.Z-SNAPSHOT</version>` → siempre el último build (ideal para integrar).  
- **Release**: `<version>X.Y.Z</version>` → inmutable, recomendado para producción.  
- Flujo típico:
  1. Trabajar en `main` con snapshot y publicar con `./mvnw clean deploy`.
  2. Cortar release (set versión estable, deploy, tag `vX.Y.Z`, push).
  3. Subir a `X.Y.(Z+1)-SNAPSHOT` para continuar desarrollo.
