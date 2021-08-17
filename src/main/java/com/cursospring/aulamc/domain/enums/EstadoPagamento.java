package com.cursospring.aulamc.domain.enums;

public enum EstadoPagamento {
   PENDENTE(1, "Pagamento Pendente"),
   QUITADO(2, "Pagamento Quitado"),
   CANCELADO(3, "Pagamento Cancelado");
   
   private int cod;
   private String descricao; 

   private EstadoPagamento(int cod, String descricao){

   }

   public String getDescricao() {
      return descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public int getCod() {
      return cod;
   }

   public void setCod(int cod) {
      this.cod = cod;
   }

   public static EstadoPagamento toEnum(Integer cod){
      if(cod == null)
         return null;
      for(EstadoPagamento x : EstadoPagamento.values()){
         if(cod.equals(x.getCod()))
            return x;
      }
      throw new IllegalArgumentException("Id invalido "+ cod);
   }
   
}
