package conta;

import cliente.Cliente;

public class ContaBonificada extends Conta {

  private double bonus;

  public ContaBonificada(String numero, double saldo, Cliente cliente) {
    super(numero,saldo,cliente);
    this.bonus = 0;
  }

  /**
   * M�todo redefinido para se adequar a classe decontas bonificadas
   * @param valor o valor a ser creditado.
   */
  public void creditar(double valor){
    this.bonus = bonus + valor;
    super.creditar(valor);
  }

  public void renderBonus(){
    super.creditar(this.bonus);
    bonus = 0;
  }
}