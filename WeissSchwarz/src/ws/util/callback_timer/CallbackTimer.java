package ws.util.callback_timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 10:59:13 AM - May 11, 2013
 * Description :
 *   
 */
public class CallbackTimer {

	public CallbackTimer(int delay, long period) {
		m_Delay = delay;
		m_Period = period;
		m_Timer = new Timer();
		class DefaultCallbackEvent implements CallbackEvent {
			@Override
			public void fireEvent() {
				System.out.printf("[%s] default CallbackEvent is not registered\n", this.toString());
			}
			
		}
		m_CallbackEvent = new DefaultCallbackEvent();
		m_TimerTask = new TimerTask() {
			@Override
			public void run() {
				m_CallbackEvent.fireEvent();
			}
		};
	}

	public void registerCallbackFunc(CallbackEvent cbEvent) {
		m_CallbackEvent = cbEvent;
	}
	
	public void start() {
		m_Timer.schedule(m_TimerTask, m_Delay, m_Period);
	}
	
	public void stop() {
		m_Timer.cancel();
	}
	
	public static void main(String[] args) {
		CallbackTimer cbTimer = new CallbackTimer(0, 1000);
		class MyCallbackEvent implements CallbackEvent {
			@Override
			public void fireEvent() {
				System.out.println("MyCallback\n");
			}
			
		}
		cbTimer.registerCallbackFunc(new MyCallbackEvent());
		cbTimer.start();
	}
	
	private final int m_Delay;
	private final long m_Period;
	private final Timer m_Timer;
	private final TimerTask m_TimerTask;
	private CallbackEvent m_CallbackEvent = null;
}
