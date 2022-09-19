package app

import kotlinx.browser.document

interface CityGuesserFeatureFlags {
    val enableWasmCompose: Boolean
}

@JsName("f_CityGuesserFeatureFlags")
fun CityGuesserFeatureFlags(): CityGuesserFeatureFlags = JsCityGuesserFeatureFlags()

class JsCityGuesserFeatureFlags: CityGuesserFeatureFlags {
    override val enableWasmCompose get() = document.location?.search.orEmpty().contains("t=wasm")
}