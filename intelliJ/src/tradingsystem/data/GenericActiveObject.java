package tradingsystem.data;

import java.util.concurrent.BlockingQueue;

public class GenericActiveObject {

	private BlockingQueue<Runnable> listaDeTarefas;

	/**
	 * 
	 * @param tarefa
	 */
	public void submit(Runnable tarefa) {
		// TODO - implement GenericActiveObject.submit
		throw new UnsupportedOperationException();
	}

}