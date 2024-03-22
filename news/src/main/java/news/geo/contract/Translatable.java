package news.geo;

@FunctionalInterface
public interface Translatable {
    /**
     * Translate this object with relative vector (deltaX, deltaY)
     * and returns a reference to itself
     * @param deltaX relative horizontal shift
     * @param deltaY relative vertical shift
     * @return reference to itself
     */
    Translatable translate(double deltaX, double deltaY);
}
