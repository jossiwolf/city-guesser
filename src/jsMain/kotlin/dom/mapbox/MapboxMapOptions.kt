package dom.mapbox

import androidx.compose.runtime.Stable
/*
@Stable
class mapbox.MapboxMapOptions(
    antialias: Boolean? = null,
    attributionControl: Boolean? = null,
    bearing: Number? = null,
    bearingSnap: Number? = null,
    bounds: dynamic = null */
/* LngLatBounds? | JsTuple<dynamic, dynamic> | JsTuple<Number, Number, Number, Number> | JsTuple<Number, Number> | LngLat? | `T$1`? | `T$2`? *//*
,
    boxZoom: Boolean? = null,
    center: dynamic = null */
/* JsTuple<Number, Number> | LngLat? | `T$1`? | `T$2`? *//*
,
    clickTolerance: Number? = null,
    collectResourceTiming: Boolean? = null,
    crossSourceCollisions: Boolean? = null,
    container: dynamic = null*/
/* String | HTMLElement *//*
,
    cooperativeGestures: Boolean? = null,
    customAttribution: dynamic = null*/
/* String? | Array<String>? *//*
,
    dragPan: dynamic = null*/
/* Boolean? | DragPanOptions? *//*
,
    dragRotate: Boolean? = null,
    doubleClickZoom: Boolean? = null,
    hash: dynamic = null */
/* Boolean? | String? *//*
,
    fadeDuration: Number? = null,
    failIfMajorPerformanceCaveat: Boolean? = null,
    fitBoundsOptions: mapbox.MapboxGl.FitBoundsOptions? = null,
    interactive: Boolean? = null,
    keyboard: Boolean? = null,
    //locale: `T$9`? = null,
    localFontFamily: String? = null,
    localIdeographFontFamily: String? = null,
    logoPosition: String? = null*/
/* "top-left" | "top-right" | "bottom-left" | "bottom-right" *//*
,
    maxBounds: dynamic = null*/
/* LngLatBounds? | JsTuple<dynamic, dynamic> | JsTuple<Number, Number, Number, Number> | JsTuple<Number, Number> | LngLat? | `T$1`? | `T$2`? *//*
,
    maxPitch: Number? = null,
    maxZoom: Number? = null,
    minPitch: Number? = null,
    minZoom: Number? = null,
    optimizeForTerrain: Boolean? = null,
    preserveDrawingBuffer: Boolean? = null,
    pitch: Number? = null,
    pitchWithRotate: Boolean? = null,
    refreshExpiredTiles: Boolean? = null,
    renderWorldCopies: Boolean? = null,
    scrollZoom: dynamic = null*/
/* Boolean? | InteractiveOptions? *//*
,
    style: dynamic = null*/
/* mapboxgl.Style? | String? *//*
,
    trackResize: Boolean? = null,
    transformRequest: mapbox.MapboxGl.TransformRequestFunction? = null,
    touchZoomRotate: dynamic = null*/
/* Boolean? | InteractiveOptions? *//*
,
    touchPitch: dynamic = null*/
/* Boolean? | InteractiveOptions? *//*
,
    zoom: Number? = null,
    maxTileCacheSize: Number? = null,
    accessToken: String? = null,
    testMode: Boolean? = null
) : mapbox.MapboxGl.MapboxOptions {
    override var antialias: Boolean?
        get() = super.antialias
        set(value) {
            super.antialias = value
        }
    override var attributionControl: Boolean?
        get() = super.attributionControl
        set(value) {
            super.attributionControl = value
        }
    override var bearing: Number?
        get() = super.bearing
        set(value) {
            super.bearing = value
        }
    override var bearingSnap: Number?
        get() = super.bearingSnap
        set(value) {
            super.bearing = value
        }
    override var bounds: dynamic
        get() = super.bounds
        set(value) {
            super.bounds = value
        }
    override var boxZoom: Boolean?
        get() = super.boxZoom
        set(value) {
            super.boxZoom = value
        }
    override var center: dynamic
        get() = super.center
        set(value) {
            super.center = value
        }
    override var clickTolerance: Number?
        get() = super.clickTolerance
        set(value) {
            super.clickTolerance = value
        }
    override var collectResourceTiming: Boolean?
        get() = super.collectResourceTiming
        set(value) {
            super.collectResourceTiming = value
        }
    override var crossSourceCollisions: Boolean?
        get() = super.crossSourceCollisions
        set(value) {
            super.crossSourceCollisions = value
        }
    override var container: dynamic
        get() = super.container
        set(value) {
            super.container = value
        }
    override var cooperativeGestures: Boolean?
        get() = super.cooperativeGestures
        set(value) {
            super.cooperativeGestures = value
        }
    override var customAttribution: dynamic
        get() = super.customAttribution
        set(value) {
            super.customAttribution = value
        }
    override var dragPan: dynamic
        get() = super.dragPan
        set(value) {
            super.dragPan = value
        }
    override var dragRotate: Boolean?
        get() = super.dragRotate
        set(value) {
            super.dragRotate = value
        }
    override var doubleClickZoom: Boolean?
        get() = super.doubleClickZoom
        set(value) {
            super.doubleClickZoom = value
        }
    override var hash: dynamic
        get() = super.hash
        set(value) {
            super.hash = value
        }
    override var fadeDuration: Number?
        get() = super.fadeDuration
        set(value) {
            super.fadeDuration = value
        }
    override var failIfMajorPerformanceCaveat: Boolean?
        get() = super.failIfMajorPerformanceCaveat
        set(value) {
            super.failIfMajorPerformanceCaveat = value
        }
    override var fitBoundsOptions: FitBoundsOptions?
        get() = super.fitBoundsOptions
        set(value) {
            super.fitBoundsOptions = value
        }
    override var interactive: Boolean?
        get() = super.interactive
        set(value) {
            super.interactive = value
        }
    override var keyboard: Boolean?
        get() = super.keyboard
        set(value) {
            super.keyboard = value
        }
    override var locale: `T$9`?
        get() = super.locale
        set(value) {
            super.locale = value
        }
    override var localFontFamily: String?
        get() = super.localFontFamily
        set(value) {
            super.localFontFamily = value
        }
    override var localIdeographFontFamily: String?
        get() = super.localIdeographFontFamily
        set(value) {
            super.localIdeographFontFamily = value
        }
    override var logoPosition: String?
        get() = super.logoPosition
        set(value) {
            super.logoPosition = value
        }
    override var maxBounds: dynamic
        get() = super.maxBounds
        set(value) {
            super.maxBounds = value
        }
    override var maxPitch: Number?
        get() = super.maxPitch
        set(value) {
            super.maxPitch = value
        }
    override var maxZoom: Number?
        get() = super.maxZoom
        set(value) {
            super.maxZoom = value
        }
    override var minPitch: Number?
        get() = super.minPitch
        set(value) {
            super.minPitch = value
        }
    override var minZoom: Number?
        get() = super.minZoom
        set(value) {
            super.minZoom = value
        }
    override var optimizeForTerrain: Boolean?
        get() = super.optimizeForTerrain
        set(value) {
            super.optimizeForTerrain = value
        }
    override var preserveDrawingBuffer: Boolean?
        get() = super.preserveDrawingBuffer
        set(value) {
            super.preserveDrawingBuffer = value
        }
    override var pitch: Number?
        get() = super.pitch
        set(value) {
            super.pitch = value
        }
    override var pitchWithRotate: Boolean?
        get() = super.pitchWithRotate
        set(value) {
            super.pitchWithRotate = value
        }
    override var refreshExpiredTiles: Boolean?
        get() = super.refreshExpiredTiles
        set(value) {
            super.refreshExpiredTiles = value
        }
    override var renderWorldCopies: Boolean?
        get() = super.renderWorldCopies
        set(value) {
            super.renderWorldCopies = value
        }
    override var scrollZoom: dynamic
        get() = super.scrollZoom
        set(value) {
            super.scrollZoom = value
        }
    override var style: dynamic
        get() = super.style
        set(value) {
            super.style = value
        }
    override var trackResize: Boolean?
        get() = super.trackResize
        set(value) {
            super.trackResize = value
        }
    override var transformRequest: TransformRequestFunction?
        get() = super.transformRequest
        set(value) {
            super.transformRequest = value
        }
    override var touchZoomRotate: dynamic
        get() = super.touchZoomRotate
        set(value) {
            super.touchZoomRotate = value
        }
    override var touchPitch: dynamic
        get() = super.touchPitch
        set(value) {
            super.touchPitch = value
        }
    override var zoom: Number?
        get() = super.zoom
        set(value) {
            super.zoom = value
        }
    override var maxTileCacheSize: Number?
        get() = super.maxTileCacheSize
        set(value) {
            super.maxTileCacheSize = value
        }
    override var accessToken: String?
        get() = super.accessToken
        set(value) {
            super.accessToken = value
        }
    override var testMode: Boolean?
        get() = super.testMode
        set(value) {
            super.testMode = value
        }

    init {
        this.antialias = antialias
        this.attributionControl = attributionControl
        this.center = center
        this.container = container
        this.style = style
        this.accessToken = accessToken
        this.testMode = testMode
        this.maxTileCacheSize = maxTileCacheSize
        this.zoom = zoom
        this.minZoom = minZoom
        this.maxZoom = maxZoom


        this.bearing = bearing
        this.bearingSnap = bearingSnap
        this.bounds = bounds
        this.boxZoom = boxZoom
        this.center = center
        this.clickTolerance = clickTolerance
        this.collectResourceTiming = collectResourceTiming
        this.crossSourceCollisions = crossSourceCollisions
        this.container = container
        this.cooperativeGestures = cooperativeGestures
        this.customAttribution = customAttribution
        this.dragPan = dragPan
        this.dragRotate = dragRotate
        this.doubleClickZoom = doubleClickZoom
        this.hash = hash
        this.fadeDuration = fadeDuration
        this.failIfMajorPerformanceCaveat = failIfMajorPerformanceCaveat
        this.fitBoundsOptions = fitBoundsOptions
        this.interactive = interactive
        this.keyboard = keyboard
        this.locale = locale
        this.localFontFamily = localFontFamily
        this.localIdeographFontFamily = localIdeographFontFamily
        this.logoPosition = logoPosition
        this.maxBounds = maxBounds
        this.maxPitch = maxPitch
        this.maxZoom = maxZoom
        this.minPitch = minPitch
        this.minZoom = minZoom
        this.optimizeForTerrain = optimizeForTerrain
        this.preserveDrawingBuffer = preserveDrawingBuffer
        this.pitch = pitch
        this.pitchWithRotate = pitchWithRotate
        this.refreshExpiredTiles = refreshExpiredTiles
        this.renderWorldCopies = renderWorldCopies
        this.scrollZoom = scrollZoom
        this.style = style
        this.trackResize = trackResize
        this.transformRequest = transformRequest
        this.touchZoomRotate = touchZoomRotate
        this.touchPitch = touchPitch
    }
}*/
