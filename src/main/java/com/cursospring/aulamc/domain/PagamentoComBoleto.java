package com.cursospring.aulamc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.cursospring.aulamc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento  {

   private Date dataVencimento;
   private Date dataPagamento;
   
   public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento,
         Date dataPagamento) {
      super(id, estado, pedido);
      this.dataVencimento = dataVencimento;
      this.dataPagamento = dataPagamento;
   }


   public PagamentoComBoleto() {
   }
   
   public Date getDataVencimento() {
      return dataVencimento;
   }
   public Date getDataPagamento() {
      return dataPagamento;
   }
   public void setDataPagamento(Date dataPagamento) {
      this.dataPagamento = dataPagamento;
   }
   public void setDataVencimento(Date dataVencimento) {
      this.dataVencimento = dataVencimento;
   }
}
