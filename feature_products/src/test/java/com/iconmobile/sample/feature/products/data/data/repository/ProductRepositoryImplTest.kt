package com.iconmobile.sample.feature.products.data.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.iconmobilesample.retrofit.ResultWrapper
import com.example.iconmobilesample.retrofit.toDomainResultWrapper
import com.iconmobile.sample.feature.products.data.data.DataFixtures
import com.iconmobile.sample.feature.products.data.data.DataFixtures.PRODUCT_ID
import com.iconmobile.sample.feature.products.data.CoroutineRule
import com.iconmobile.sample.feature.products.data.mapper.ProductDtoToDomainMapper
import com.iconmobile.sample.feature.products.data.repository.LoadProductRepositoryImpl
import com.iconmobile.sample.feature.products.data.retrofit.service.RetrofitService
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductRepositoryImplTest {
    @Mock
    internal lateinit var mockService: RetrofitService

    @Mock
    internal lateinit var mapper: ProductDtoToDomainMapper

    private lateinit var cut: LoadProductRepositoryImpl


    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        cut = LoadProductRepositoryImpl(mockService, mapper)
    }

    @Test
    fun `loadProduct fetches Product and maps to Model`() {
        runBlocking {
            // given
            given(mockService.loadProduct(PRODUCT_ID))
                .willReturn(DataFixtures.getProductDto())

            // when
            val result = cut.getProduct(PRODUCT_ID)

            // then
            result shouldBeEqualTo ResultWrapper.Success(
                mapper.transform(DataFixtures.getProductDto())
            ).toDomainResultWrapper()
        }
    }
}