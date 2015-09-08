package eden.project.model;

public class Table {
	private int tableId;
	private int reservationId;
	private int customerId;
	private int tableCapacity;
	private String tableStatus;
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getTableCapacity() {
		return tableCapacity;
	}
	public void setTableCapacity(int tableCapacity) {
		this.tableCapacity = tableCapacity;
	}
	public String getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}
}
