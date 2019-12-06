package dbos;

public class Aluno implements Cloneable{
	private int ra;
	private String nome;
	private String email;
	
	public Aluno(int ra, String nome,String email) throws Exception
	{
		this.setRa(ra);
		this.setNome(nome);
		this.setEmail(email);
	}
	
	public int getRa()
	{
		return this.ra;
	}
	public String getNome()
	{
		return this.nome;
	}
	public String getEmail()
	{
		return this.email;
	}
	public void setRa(int ra) throws Exception
	{
		//if(ra <= 9999)
		//	throw new Exception("RA invalido!");
		
		this.ra = ra;
	}
	public void setNome(String nome) throws Exception
	{
		if(nome == null || nome.equals(""))
			throw new Exception("Nome Invalido!");
                if(temNumero(nome))
                   throw new Exception("Existe um Numero no seu Nome!"); 
		
		this.nome = nome;
	}
        private boolean temNumero(String nome)
        {
            boolean retorno = false;
            int valor;
            for(int i=0; i < nome.length();i++)
            {
                try{
                valor = Integer.parseInt(nome.charAt(i)+"");
                if(valor == new Integer(valor))
                    retorno = true;
                }catch(Exception erro)
                {}
            }
            
            return retorno;
        }
	public void setEmail(String email) throws Exception
	{
		if(email == null || email.equals(""))
			throw new Exception("Email Invalido!");
		if(!temArroba(email))
			throw new Exception("Email Invalido pois não tem @ !");
		if(!temCom(email))
			throw new Exception("Email Invalido pois não tem .com");
		this.email = email;
	}
	
	private boolean temArroba(String email) {
		boolean retorno = false;
		for(int i=0; i < email.length();i++) {
			if(email.charAt(i) == '@')
				retorno = true;
		}
		return retorno;
	}
	public boolean temCom(String email)
	{
		String pCom = ".com";
		String com = "";
		boolean retorno = false;
		int comeco = email.length() - 4;
		for(int i = comeco; i < email.length();i++ )
		{
			
			com += email.charAt(i)+"";
		}
		if(pCom.equals(com))
			retorno = true;
		
		return retorno;
	}
	public String toString()
	{
		return "RA: "+ this.getRa() + 
				"\n Nome: "+ this.getNome() + 
				"\n Email: "+ this.getEmail();
	}
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(obj.getClass() != this.getClass())
			return false;
		Aluno aux = (Aluno)obj;
		if(aux.ra != this.ra)
			return false;
		if(!aux.nome.equals(this.nome))
			return false;
		if(!aux.email.equals(this.email))
			return false;
		
		return true;
	}
	public int hashCode()
	{
		int ret = 1;
		
		ret = ret * 7 + new Integer(this.ra).hashCode();
		ret = ret * 7 + this.nome.hashCode();
		ret = ret * 7 + this.email.hashCode();
		
		if(ret < 0)
			ret -= ret;
		
		return ret;
	}
	
	public Aluno(Aluno mold) throws Exception 
	{
		if(mold == null)
			throw new Exception("Mold Invalido!");
		
		this.ra = mold.ra;
		this.nome = mold.nome;
		this.email = mold.email;
	}
	
	public Object clone()
	{
		Aluno ret = null;
		try {
			ret = new Aluno(this);
		}catch(Exception erro) {}
		
		return ret;
	}
}
