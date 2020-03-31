package com.iconmobile.sample.library.base.data.viewmodelmapper

interface DomainToDataMapper<in Domain, out Data> {

    fun transform(view: Domain): Data
}