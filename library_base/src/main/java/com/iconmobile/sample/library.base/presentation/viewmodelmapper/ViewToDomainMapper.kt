package com.iconmobile.sample.library.base.presentation.viewmodelmapper

interface ViewToDomainMapper<in View, out Domain> {

    fun transform(view: View): Domain
}