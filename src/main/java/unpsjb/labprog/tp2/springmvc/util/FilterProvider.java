package unpsjb.labprog.tp2.springmvc.util;

import java.util.Date;

public class FilterProvider {
	
	private long shipmentProviderId ;
	private Date beginDate;
	private Date endDate;
	private String providerName;
	
	public FilterProvider(){
		
	}
	
	public FilterProvider(long shipmentProviderId, Date beginDate, Date endDate, String providerName) {
		super();
		this.shipmentProviderId = shipmentProviderId;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.providerName = providerName;
	}
	public long getShipmentProviderId() {
		return shipmentProviderId;
	}
	public void setShipmentProviderId(long shipmentProviderId) {
		this.shipmentProviderId = shipmentProviderId;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	@Override
	public String toString() {
		return "FilterProvider [shipmentProviderId=" + shipmentProviderId + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", providerName=" + providerName + "]";
	}
	
	

}
