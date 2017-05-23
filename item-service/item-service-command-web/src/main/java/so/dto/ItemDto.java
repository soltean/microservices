package so.dto;

/**
 * Created by sergiu.oltean on 5/8/2017.
 */
public class ItemDto {

	private String itemCode;
	private int reservePrice;

	public ItemDto(String itemCode, int reservePrice) {
		this.itemCode = itemCode;
		this.reservePrice = reservePrice;
	}

	public String getItemCode() {
		return itemCode;
	}

	public int getReservePrice() {
		return reservePrice;
	}
}
