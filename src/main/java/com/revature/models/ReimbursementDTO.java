package com.revature.models;

import java.sql.Timestamp;

import com.revature.models.Reimbursement.ReimbursementStatus;
import com.revature.models.Reimbursement.ReimbursementType;
//idk if I need this...
//maybe I do need it bc a new reimb is never coming in with a resolver
//or maybe I need it bc a new reimb is coming in with a string author, so in the service I send in with string
public class ReimbursementDTO {
	public int reimbID;
	public double reimbAmount;
	
	//don't think timestamp will come in from frontend
	public String timeSubmitted;
	public String timeResolved;
	
	public String reimbDescription;
	public String authorUserName;
	
	//won't come in with a resolver
	public String resolverUserName;
	
	//status should automatically come in as pending
	public ReimbursementStatus reimbStatus;
	
	public ReimbursementType reimbType;
	
	public ReimbursementDTO() {
		super();
	}

	public ReimbursementDTO(int reimbID, double reimbAmount, String timeSubmitted, String timeResolved,
			String reimbDescription, String authorUserName, String resolverUserName, ReimbursementStatus reimbStatus,
			ReimbursementType reimbType) {
		super();
		this.reimbID = reimbID;
		this.reimbAmount = reimbAmount;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.reimbDescription = reimbDescription;
		this.authorUserName = authorUserName;
		this.resolverUserName = resolverUserName;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}

	public ReimbursementDTO(double reimbAmount, String timeSubmitted, String timeResolved,
			String reimbDescription, String authorUserName, String resolverUserName, ReimbursementStatus reimbStatus,
			ReimbursementType reimbType) {
		super();
		this.reimbAmount = reimbAmount;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.reimbDescription = reimbDescription;
		this.authorUserName = authorUserName;
		this.resolverUserName = resolverUserName;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}

	@Override
	public String toString() {
		return "ReimbursementDTO [reimbID=" + reimbID + ", reimbAmount=" + reimbAmount + ", timeSubmitted="
				+ timeSubmitted + ", timeResolved=" + timeResolved + ", reimbDescription=" + reimbDescription
				+ ", authorUserName=" + authorUserName + ", resolverUserName=" + resolverUserName + ", reimbStatus="
				+ reimbStatus + ", reimbType=" + reimbType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorUserName == null) ? 0 : authorUserName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reimbAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + reimbID;
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + ((resolverUserName == null) ? 0 : resolverUserName.hashCode());
		result = prime * result + ((timeResolved == null) ? 0 : timeResolved.hashCode());
		result = prime * result + ((timeSubmitted == null) ? 0 : timeSubmitted.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementDTO other = (ReimbursementDTO) obj;
		if (authorUserName == null) {
			if (other.authorUserName != null)
				return false;
		} else if (!authorUserName.equals(other.authorUserName))
			return false;
		if (Double.doubleToLongBits(reimbAmount) != Double.doubleToLongBits(other.reimbAmount))
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (reimbID != other.reimbID)
			return false;
		if (reimbStatus != other.reimbStatus)
			return false;
		if (reimbType != other.reimbType)
			return false;
		if (resolverUserName == null) {
			if (other.resolverUserName != null)
				return false;
		} else if (!resolverUserName.equals(other.resolverUserName))
			return false;
		if (timeResolved == null) {
			if (other.timeResolved != null)
				return false;
		} else if (!timeResolved.equals(other.timeResolved))
			return false;
		if (timeSubmitted == null) {
			if (other.timeSubmitted != null)
				return false;
		} else if (!timeSubmitted.equals(other.timeSubmitted))
			return false;
		return true;
	}


}
