package dbos;

public class Materia implements Cloneable{
	private int codigoMateria;
	private String nomeMateria;
	
	public Materia(int codigo, String nome) throws Exception
	{
		this.setCodigoMateria(codigo);
		this.setNomeMateria(nome);
	}
	public int getCodigoMateria()
	{
		return this.codigoMateria;
	}
	public String getNomeMateria()
	{
		return this.nomeMateria;
	}
	public void setCodigoMateria(int codigo) throws Exception
	{
		if(codigo <= 0 )
			throw new Exception("Codigo invalido!");
		this.codigoMateria = codigo;
	}
	public void setNomeMateria(String nome) throws Exception
	{
		if(nome == null || nome.equals(""))
			throw new Exception("Novo Nome Invalido!");
		
		this.nomeMateria = nome;
	}
	
	public String toString()
	{
		return "Codigo: "+ this.getCodigoMateria() + "\n Materia: "+this.getNomeMateria();
	}
	public int hashCode()
	{
		int ret = 1;
		ret = ret * 7 + new Integer(this.codigoMateria).hashCode();
		ret = ret * 7 + nomeMateria.hashCode();
		
		if(ret < 0)
			ret -= ret;
		
		return ret;
	}
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(obj.getClass() != this.getClass())
			return false;
		Materia aux = (Materia)obj;
		if(aux.codigoMateria != this.codigoMateria)
			return false;
		if(!aux.nomeMateria.equals(this.nomeMateria))
			return false;
		
		return true;
	}
	
	public Materia(Materia mold) throws Exception
	{
		if(mold == null)
			throw new Exception("Mold Invalido!");
		
		this.codigoMateria = mold.codigoMateria;
		this.nomeMateria = mold.nomeMateria;
	}
	public Object clone()
	{
		Object ret = null;
		try {
			ret = new Materia(this);
		}catch(Exception erro) {}
		return ret;
	}
}
