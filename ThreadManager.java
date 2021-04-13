package mailSender;

import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class ThreadManager {

	public ArrayList<Thread> threads = new ArrayList<>();
	public static void main(String[] args) {
		

	}
	
	public void createThreads(int threadCount,Message message)
	{
		for(int i =0; i < threadCount; i++)
		{
			Thread thread = new Thread(new Runnable()
					{

						public void run() {
							try {
								Transport.send(message);
								System.out.println("Done");
							} catch (MessagingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				
					}
					);
			threads.add(thread);
		}
	}
	
	public void startThreads()
	{
		for(Thread thread : threads)
		{
			thread.start();
		}
	}

}
