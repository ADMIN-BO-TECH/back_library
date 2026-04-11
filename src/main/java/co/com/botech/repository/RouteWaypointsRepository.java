package co.com.botech.repository;

import co.com.botech.entity.RouteWaypoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RouteWaypointsRepository extends JpaRepository<RouteWaypoints, Long> {
    List<RouteWaypoints> findByRouteIdOrderByOrderIndexAsc(Long routeId);
    Optional<RouteWaypoints> findByRouteIdAndId(Long routeId, Long id);
}

