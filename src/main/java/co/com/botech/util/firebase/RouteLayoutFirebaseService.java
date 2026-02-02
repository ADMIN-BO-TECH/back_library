package co.com.botech.util.firebase;

import co.com.botech.constants.FirebaseCollectionsConstants;
import co.com.botech.dto.route.RouteLayoutDto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteLayoutFirebaseService {

    private final Firestore firestore;

    public void upsert(RouteLayoutDto dto) {
        String routeId = dto.getRouteId().trim();

        try {
            DocumentReference mainDoc = firestore
                    .collection(FirebaseCollectionsConstants.MAP_TRACE.getName())
                    .document(routeId);

            CollectionReference coordsCol = mainDoc
                    .collection(FirebaseCollectionsConstants.MAP_TRACE_COORDINATES.getName());

            // Main data
            Map<String, Object> mainData = new HashMap<>();
            mainData.put("routeId", dto.getRouteId());
            mainData.put("totalEncryptedPolyline", dto.getTotalEncryptedPolyline());
            mainData.put("totalDistance", dto.getTotalDistance());
            mainData.put("startAddress", dto.getStartAddress());
            mainData.put("endAddress", dto.getEndAddress());
            mainData.put("totalEstimatedTime", dto.getTotalEstimatedTime());

            mainDoc.set(mainData).get();

            // Delete existing coordinates
            ApiFuture<QuerySnapshot> existingFuture = coordsCol.get();
            List<QueryDocumentSnapshot> existingDocs = existingFuture.get().getDocuments();

            WriteBatch batch = firestore.batch();

            for (QueryDocumentSnapshot d : existingDocs) {
                batch.delete(d.getReference());
            }

            // Insert new coordinates
            if (dto.getCoordinates() != null) {
                for (RouteLayoutDto.CoordinateDto c : dto.getCoordinates()) {
                    if (c == null || c.getOrder() == null) continue;

                    Map<String, Object> data = new HashMap<>();
                    data.put("order", c.getOrder());
                    data.put("latitude", c.getLatitude());
                    data.put("longitude", c.getLongitude());
                    data.put("waypointSegmentDistance", c.getWaypointSegmentDistance());
                    data.put("waypointSegmentStartAddress", c.getWaypointSegmentStartAddress());
                    data.put("waypointSegmentEndAddress", c.getWaypointSegmentEndAddress());
                    data.put("waypointSegmentEstimatedTime", c.getWaypointSegmentEstimatedTime());

                    batch.set(coordsCol.document(c.getOrder()), data);
                }
            }

            batch.commit().get();

        } catch (InterruptedException | ExecutionException e) {
            log.error("[Firebase] RouteLayout upsert failed routeId={}", routeId, e);
            throw new RuntimeException("Error saving route layout to Firebase", e);
        }
    }

    public RouteLayoutDto getByRouteId(String routeId) {
        try {
            String id = routeId.trim();

            DocumentReference mainDoc = firestore
                    .collection(FirebaseCollectionsConstants.MAP_TRACE.getName())
                    .document(id);

            DocumentSnapshot mainSnap = mainDoc.get().get();

            if (!mainSnap.exists()) return null;

            RouteLayoutDto dto = new RouteLayoutDto();
            dto.setRouteId(id);
            dto.setTotalEncryptedPolyline(mainSnap.getString("totalEncryptedPolyline"));
            dto.setTotalDistance(mainSnap.getString("totalDistance"));
            dto.setStartAddress(mainSnap.getString("startAddress"));
            dto.setEndAddress(mainSnap.getString("endAddress"));
            dto.setTotalEstimatedTime(mainSnap.getString("totalEstimatedTime"));

            CollectionReference coordsCol = mainDoc
                    .collection(FirebaseCollectionsConstants.MAP_TRACE_COORDINATES.getName());

            QuerySnapshot coordsSnap = coordsCol.get().get();

            List<RouteLayoutDto.CoordinateDto> coords = new ArrayList<>();
            for (QueryDocumentSnapshot d : coordsSnap.getDocuments()) {
                RouteLayoutDto.CoordinateDto c = new RouteLayoutDto.CoordinateDto();
                c.setOrder(d.getString("order"));
                c.setLatitude(d.getString("latitude"));
                c.setLongitude(d.getString("longitude"));
                c.setWaypointSegmentDistance(d.getString("waypointSegmentDistance"));
                c.setWaypointSegmentStartAddress(d.getString("waypointSegmentStartAddress"));
                c.setWaypointSegmentEndAddress(d.getString("waypointSegmentEndAddress"));
                c.setWaypointSegmentEstimatedTime(d.getString("waypointSegmentEstimatedTime"));
                coords.add(c);
            }

            dto.setCoordinates(coords);
            return dto;

        } catch (Exception e) {
            log.error("[Firebase] RouteLayout get failed routeId={}", routeId, e);
            return null;
        }
    }
}