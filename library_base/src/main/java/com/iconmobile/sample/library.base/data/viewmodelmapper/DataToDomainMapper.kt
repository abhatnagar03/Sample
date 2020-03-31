package com.iconmobile.sample.library.base.data.viewmodelmapper

interface DataToDomainMapper<in Data, out Domain> {

   fun transform(domain: Data): Domain
}