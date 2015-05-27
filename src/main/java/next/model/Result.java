package next.model;

public class Result {
	
	private String status;
	private String Reason;
	
	public Result(String status, String reason) {
		this.status = status;
		this.Reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}
}
