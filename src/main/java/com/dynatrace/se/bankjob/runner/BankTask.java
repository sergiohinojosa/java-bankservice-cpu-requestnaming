/**
 * 
 */
package com.dynatrace.se.bankjob.runner;

import static com.dynatrace.se.bankjob.util.Helper.getRandomElement;
import static com.dynatrace.se.bankjob.util.Helper.getRandomNumberInRange;

import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.dynatrace.se.bankjob.data.BankData;
import com.dynatrace.se.bankjob.fibonacci.Fibonacci;
import com.dynatrace.se.bankjob.util.Helper;
import com.dynatrace.se.bankjob.util.Job;

/**
 * @author sergio.hinojosa
 *
 */
public class BankTask {

	private static Logger logger = Logger.getLogger(BankTask.class.getName());
	private static BankData data = BankData.getInstance();
	private static HttpClient client;

	/**
	 * Main method
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// Initiate the amount of Threads
		logger.info("Amount of threads " + data.threads);
		for (int i = 0; i < data.threads; i++) {
			BankThread t = new BankThread();
			t.start();
		}
		logger.info("Done initializing threads ");
	}

	/**
	 * The Job will be executed forever
	 * 
	 */
	static void loopInJobsForever() {
		boolean loopForever = true;
		while (loopForever) {
			String bank = getRandomElement(data.banks);

			String job = getRandomElement(data.jobs);
			long tid = Thread.currentThread().getId();

			try {
				int sleepTime = getRandomNumberInRange(0, data.sleepTime);
				logger.info("Execute job [" + job + "] - Bank[" + bank + "]" + " sleep [" + sleepTime + "]s ThreadId["
						+ tid + "]");
				executeJob(job, bank);
				TimeUnit.SECONDS.sleep(sleepTime);
			} catch (Exception e) {
				logger.severe(e.getLocalizedMessage());
			}
		}
	}

	/**
	 * 
	 * @param bank
	 * @param jobname
	 * @throws Exception
	 * 
	 */
	private static void executeJob(String jobname, String bank) throws Exception {

		switch (Job.fromString(jobname)) {
		case RISKY_JOB:
			doRiskyJob();
			break;
		case HEAVY_CALCULATION:
			doHeavyCalculation();
			break;
		case CHECK_URL:
			doCheckUrl(Helper.getRandomElement(data.urls));
			break;

		default:
			break;
		}
	}

	/**
	 * 
	 * @throws Exception
	 */
	private static void doRiskyJob() throws Exception {
		// Get Failure Rate
		int shouldFail = getRandomNumberInRange(1, 100);
		int x = 0;
		try {
			if (shouldFail < data.failurerate) {
				// Divide by Zero
				x = 1 / 0;
			} else {
				x = shouldFail / data.failurerate;
			}
		} catch (Exception e) {
			throw new Exception("Risky job failed:" + e.getLocalizedMessage(), e);
		}
	}

	/**
	 * it will calculate the Fibonacci numbers in Range from 10 to 35.
	 */
	private static void doHeavyCalculation() {
		Fibonacci.fibonacci(getRandomNumberInRange(10, 35));
	}

	/**
	 * 
	 * @param urlString
	 * @throws Exception
	 */
	static void doCheckUrl(String urlString) throws Exception {
		client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(urlString);

		HttpResponse response = client.execute(request);
		logger.info(urlString + "code:" + String.valueOf(response.getStatusLine().getStatusCode()));
	}

}
