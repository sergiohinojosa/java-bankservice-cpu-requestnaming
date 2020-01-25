package com.dynatrace.se.bankjob.runner;

public class BankThread extends Thread {
	String bank;
	String job;

	public BankThread(String bank, String job) {
		super();
		
		this.bank = bank;
		this.job = job;
		}

	public void run() {
		BankTask.loopInJobsForever();
	}
	
}

	
		