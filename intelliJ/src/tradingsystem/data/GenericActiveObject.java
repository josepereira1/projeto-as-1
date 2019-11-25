package tradingsystem.data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class GenericActiveObject {

	private BlockingQueue<Runnable> listaDeTarefas;

	public GenericActiveObject(){
		listaDeTarefas = new LinkedBlockingDeque<>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					try {
						listaDeTarefas.take().run();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	/**
	 * 
	 * @param tarefa
	 */
	public void submit(Runnable tarefa) {
		listaDeTarefas.add(tarefa);
	}

}