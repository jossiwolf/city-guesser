package data

external interface CityGuesserFeatureProperties {

    /**
     * The country the feature is located in.
     */
    @JsName("ADM0NAME")
    val country: String

    /**
     * Whether the feature is considered a megacity
     */
    @JsName("MEGACITY")
    val isMegacity: Int

    /**
     * The scale at which the feature shows up on a map when zooming in
     * https://www.naturalearthdata.com/forums/topic/definition-of-scale-rank-appropriate-attribute-for-size-based-styling/
     */
    @JsName("SCALERANK")
    val minZoom: Int

    /**
     * The feature's name, non-ascii
     */
    @JsName("NAME")
    val name: String

    /**
     * The total population of the feature
     */
    @JsName("GN_POP")
    val population: Int

    /**
     * Whether the feature is a "world city". That seems to mean.. relevant to the world? idk..
     */
    @JsName("WORLDCITY")
    val isWorldCity: Double
}