package com.iconmobile.sample.feature.products.data.domain.usecase

import com.iconmobile.sample.feature.products.data.domain.DomainFixtures
import com.iconmobile.sample.feature.products.data.domain.DomainFixtures.PRODUCT_ID
import com.iconmobile.sample.feature.products.data.repository.LoadProductRepositoryImpl
import com.iconmobile.sample.feature.products.domain.usecase.LoadProductUseCase
import com.iconmobile.sample.library.base.domain.DomainResultWrapper
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetProductUseCaseTest {

    @Mock
    internal lateinit var mockRepository: LoadProductRepositoryImpl

    private lateinit var cut: LoadProductUseCase

    @Before
    fun setUp() {
        cut = LoadProductUseCase(mockRepository)
    }

    @Test
    fun `return products`() {
        runBlocking {
            // given
            val product = DomainResultWrapper.Success(
                DomainFixtures.getProduct()
            )
            given(mockRepository.getProduct(PRODUCT_ID)).willReturn(product)

            // when
            val result = cut.execute(PRODUCT_ID)

            // then
            result shouldBeEqualTo product
        }
    }
}