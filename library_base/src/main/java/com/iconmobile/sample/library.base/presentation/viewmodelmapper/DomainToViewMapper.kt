package com.iconmobile.sample.library.base.presentation.viewmodelmapper

interface DomainToViewMapper<in Domain, out View> {

   fun transform(domain: Domain): View
}