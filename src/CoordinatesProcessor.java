package battleship;

public interface CoordinatesProcessor {

    default int[] processCoordinates(String coordinates) {
        return new int[] {
                coordinates.charAt(0) - 65,
                Integer.parseInt(coordinates.substring(1)) - 1
        };
    }

}
