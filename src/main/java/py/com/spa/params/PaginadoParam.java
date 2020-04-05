package py.com.spa.params;

public class PaginadoParam <T> {
	private T filtros;
	private Integer cantidad;
	private Integer pagina;
	private String orderBy;
	private String orderDir;
	
	
	public T getFiltros() {
		return filtros;
	}
	public void setFiltros(T filtros) {
		this.filtros = filtros;
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
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}


}
