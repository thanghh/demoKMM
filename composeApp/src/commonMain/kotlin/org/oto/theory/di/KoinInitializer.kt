package org.oto.theory.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(modules: List<Module>) {
    startKoin {
        modules(modules)

    }
}