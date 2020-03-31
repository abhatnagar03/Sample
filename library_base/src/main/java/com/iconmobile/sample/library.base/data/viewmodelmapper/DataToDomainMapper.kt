package com.iconmobile.sample.library.base.data.viewmodelmapper

interface DataToDomainMapper<in Data, out Domain> {

   fun transform(data: Data): Domain
}