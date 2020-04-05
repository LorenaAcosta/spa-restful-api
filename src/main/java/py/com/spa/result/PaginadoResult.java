package py.com.spa.result;

import java.util.List;

import org.springframework.data.domain.Page;

public class PaginadoResult  <T> {
	private List<T> lista;
	private Integer cantidad;
	private Integer pagina;
	private Integer total;
	
	public PaginadoResult(Page<T> lista) {
        super();
        this.lista = lista.getContent();
        this.cantidad = lista.getPageable().getPageSize();
        this.pagina = lista.getPageable().getPageNumber();
        this.total = (int) lista.getTotalElements() ;

    }
	
	
	public List<T> getLista() {
		return lista;
	}
	public void setLista(List<T> lista) {
		this.lista = lista;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getPagina() {
		return pagina;
	}
	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	

}
