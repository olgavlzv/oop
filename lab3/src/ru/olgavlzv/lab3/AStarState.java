package ru.olgavlzv.lab3;

import java.util.HashMap;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/

public class AStarState {
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;

    private HashMap<Location, Waypoint> openWaypoints = new HashMap<>();
    private HashMap<Location, Waypoint> closeWaypoints = new HashMap<>();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map) {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap() {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint() { //поиск в хэшмапе открытых вершин вершину с наименьшей стоимостью
        if (numOpenWaypoints() == 0) {
            return null;
        }
        else {
            Waypoint minPoint = (Waypoint) openWaypoints.values().toArray()[0];
            float minValue = minPoint.getTotalCost();
            for (int i = 0; i < openWaypoints.size(); i++) {
                Waypoint nextPoint = (Waypoint) openWaypoints.values().toArray()[i];
                if (minValue >= nextPoint.getTotalCost()) {
                    minValue = nextPoint.getTotalCost();
                    minPoint = nextPoint;
                }
            }
            return minPoint;
        }
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP) { //проверка на то, что данная вершина "лучше"
        if (!openWaypoints.containsKey(newWP.getLocation())) {
            openWaypoints.put(newWP.getLocation(), newWP);
            return true;
        }
        else {
            if (openWaypoints.get(newWP.getLocation()).getRemainingCost() > newWP.getRemainingCost()) {
                openWaypoints.put(newWP.getLocation(), newWP);
                return true;
            }
        }
        return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints() {
        return openWaypoints.size();
    } //количество точек в хэшмапе с открытыми вершинами


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc) { //перемещение вершины из хэшмапа с открытыми верш. в хэшмап и закрытыми
        Waypoint wp = openWaypoints.get(loc);
        openWaypoints.remove(loc);
        closeWaypoints.put(loc, wp);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc) {
        return openWaypoints.containsKey(loc);
    } //если проверяемая точка уже есть в хэшмапе закрытых вершин
}
