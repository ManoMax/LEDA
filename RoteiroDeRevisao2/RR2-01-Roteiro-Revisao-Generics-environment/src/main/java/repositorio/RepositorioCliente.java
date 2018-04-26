package repositorio;

import cliente.Cliente;

public class RepositorioCliente {

	private Cliente[] clientes;
	private int indice;

	public RepositorioCliente() {
		indice = 0;
		clientes = new Cliente[20];
	}

	private int procurarIndice(Cliente cliente) {
		int i = 0;
		int resp = -1;
		boolean achou = false;

		while ((i < indice) && !achou) {
			if ((clientes[i]).equals(cliente)) {
				resp = i;
				achou = true;
			}
			i = i + 1;
		}
		return resp;
	}

	public boolean existe(Cliente cliente) {
		boolean resp = false;

		int i = this.procurarIndice(cliente);
		if (i != -1) {
			resp = true;
		}

		return resp;
	}

	public void inserir(Cliente novoCliente) {
		clientes[indice] = novoCliente;
		this.indice++;
	}

	public void atualizar(Cliente cliente) {
		int i = procurarIndice(cliente);
		if (i != -1) {
			clientes[i] = cliente;
		} else {
			throw new RuntimeException("Conta nao encontrada");
		}
	}

	public Cliente procurar(Cliente cliente) {
		Cliente resp = null;
		int i = this.procurarIndice(cliente);
		if (i != -1) {
			resp = clientes[i];
		} else {
			throw new RuntimeException("Cliente nao encontrado");
		}

		return resp;
	}

	public void remover(Cliente cliente) {
		int i = this.procurarIndice(cliente);
		if (i != -1) {
			clientes[i] = clientes[indice - 1];
			clientes[indice - 1] = null;
			indice = indice - 1;
		} else {
			throw new RuntimeException("Cliente nao encontrado");
		}
	}
}