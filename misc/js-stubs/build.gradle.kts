plugins {
    id("build-logic.kotlin-js")
}

description = "not all used test-libs have a js target, we fake the signatures here"

kotlin {
    js {
        nodejs()
    }
}
