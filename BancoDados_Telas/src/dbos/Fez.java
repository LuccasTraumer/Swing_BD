package dbos;

public class Fez implements Cloneable{
	private int ra;
	private int codigoMateria;
	private float nota;
	private int frequencia;
	
        public Fez(int ra, int codigoMateria, float nota,int frequencia) throws Exception
        {
            this.setRa(ra);
            this.setCodigoMateria(codigoMateria);
            this.setNota(nota);
            this.setFrequencia(frequencia);
        }
	public int getRa() {
		return ra;
	}
	public void setRa(int ra) 
        {
		this.ra = ra;
	}
	public int getCodigoMateria() {
		return codigoMateria;
	}
	public void setCodigoMateria(int codigoMateria)  throws Exception
        {
            if(codigoMateria < 0 )
                throw new Exception("Codigo Invalido!");
		this.codigoMateria = codigoMateria;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) throws Exception
        {
            if(nota < 0 || nota > 10 )
                throw new Exception("Nota Invalida!");
		this.nota = nota;
	}
	public int getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(int frequencia) throws Exception 
        {
            if(frequencia < 0 || frequencia > 100 )
                throw new Exception("Frequencia Invalida!");
		this.frequencia = frequencia;
	}
	
	public int hashCode() {
		
		int ret = 1;
		ret = ret * 7 + new Integer(codigoMateria).hashCode();
		ret = ret * 7 + new Integer(frequencia).hashCode();
		ret = ret * 7 + new Float(this.nota).hashCode();
		ret = ret * 7 + new Integer(this.ra).hashCode();
		if(ret < 0)
			ret -= ret;
		
		return ret;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Fez aux = (Fez) obj;
		if (codigoMateria != aux.codigoMateria)
			return false;
		if (this.frequencia != aux.frequencia)
			return false;
		if (this.nota != aux.nota)
			return false;
		if (this.ra != aux.ra)
			return false;
		return true;
	}
	public String toString() {
		return "Fez [ra=" + ra + ", codigoMateria=" + codigoMateria + ", nota=" + nota + ", frequencia=" + frequencia
				+ "]";
	}
	public Fez(Fez mold) throws Exception
	{
		if(mold == null)
			throw new Exception("Mold Invalido!");
		
		this.ra = mold.ra;
		this.codigoMateria = mold.codigoMateria;
		this.nota = mold.nota;
		this.frequencia = mold.frequencia;
	}
	public Object clone()
	{
		Object ret = null;
		try {
			ret = new Fez(this);
		}catch(Exception erro) {}
		return ret;
	}
	
}
