package com.dynatrace.se.bankjob.runner;

public class BankThread extends Thread {

	public void run() {
		BankTask.loopInJobsForever();
	}

}