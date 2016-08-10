package by.darchyk.banksystem.entity;

import java.sql.Date;

public class Deposit {
	private int id;
	private int sum;
	private int term;
	private int percent;
	private Date date;
	private int depositNumber;
	private int idClient;
	
	public Deposit() {
		
	}

	public Deposit(int id, int sum, int term, int percent, Date date, int depositNumber, int idClient) {
		super();
		this.id = id;
		this.sum = sum;
		this.term = term;
		this.percent = percent;
		this.date = date;
		this.depositNumber = depositNumber;
		this.idClient = idClient;
	}

	public int getDepositNumber() {
		return depositNumber;
	}

	public void setDepositNumber(int depositNumber) {
		this.depositNumber = depositNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = new java.sql.Date(date.getTime());
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + depositNumber;
		result = prime * result + id;
		result = prime * result + idClient;
		result = prime * result + percent;
		result = prime * result + sum;
		result = prime * result + term;
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
		Deposit other = (Deposit) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (depositNumber != other.depositNumber)
			return false;
		if (id != other.id)
			return false;
		if (idClient != other.idClient)
			return false;
		if (percent != other.percent)
			return false;
		if (sum != other.sum)
			return false;
		if (term != other.term)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Deposit [id=" + id + ", sum=" + sum + ", term=" + term + ", percent=" + percent + ", date=" + date
				+ ", depositNumber=" + depositNumber + ", idClient=" + idClient + "]";
	}

	
		
}
