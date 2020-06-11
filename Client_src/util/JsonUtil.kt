package client.util

import client.data.dao.IngredientModel
import client.data.dao.ProductModel
import com.google.gson.JsonElement

/**
 * Json 데이터 Parsing을 위한 Singleton class
 * 이 부분만 kotlin 코드를 이용하였음.
 */
object JsonUtil {
    fun getProductModel(obj: JsonElement): ProductModel {
        val PrCode: Int = obj.asJsonObject.get("PrCode").asInt
        val PrName: String = obj.asJsonObject.get("PrName").asString
        val PrPrice: Int = obj.asJsonObject.get("PrPrice").asInt
        val PrNumber: Int = obj.asJsonObject.get("PrNumber").asInt
        val PrIngredient: String = obj.asJsonObject.get("PrIngredient").asString
        val isSell: Boolean = obj.asJsonObject.get("IsSell").asBoolean

        return ProductModel(PrCode, PrName, PrPrice, PrNumber, PrIngredient, isSell)
    }

    fun getIngredientModel(obj: JsonElement): IngredientModel {
        val IgCode: Int = obj.asJsonObject.get("IgCode").asInt
        val IgName: String = obj.asJsonObject.get("IgName").asString
        val IgNumber: Int = obj.asJsonObject.get("IgNumber").asInt
        val IgPrice: Int = obj.asJsonObject.get("IgPrice").asInt
        val IgProduct: String = obj.asJsonObject.get("IgProduct").asString
        return IngredientModel(IgCode, IgName, IgNumber, IgPrice, IgProduct)
    }
}