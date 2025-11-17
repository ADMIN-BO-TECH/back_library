# back_library – Guía rápida de configuración y despliegue (terminal)

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

# 1.2 forzar a Maven a usar ese settings: OJO son dos líneas exactas
printf -- "%s\n%s\n" "-s" ".mvn/settings.xml" > .mvn/maven.config

# 1.3 ignorar estos archivos locales
printf "%s\n%s\n" ".mvn/settings.xml" ".mvn/maven.config" >> .gitignore

# 1.4 verificación
./mvnw help:effective-settings -Doutput=.mvn/effective.xml
grep -A2 "<id>github</id>" .mvn/effective.xml
```
## 2) POM mínimo para publicar a GitHub Packages

Asegúrate de que el pom.xml tenga este bloque (el <id> debe coincidir con el de settings.xml):

```bash
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
