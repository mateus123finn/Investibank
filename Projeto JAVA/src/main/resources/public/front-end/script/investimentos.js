
function openNav() 
{
    document.getElementById("myNav").style.width = "100%";
}
  

function closeNav() 
{
    document.getElementById("myNav").style.width = "0%";
}

function getQuant() 
{
    var quant = document.getElementById('quant').value;

    if( !(quant >= 'a' && quant<= 'z') ) 
    {
        if(quant <= 0)
        {
            window.alert('Compra não efetuada, quantidade inválida');
        }
        else
        {
            window.alert('Compra efetuada com sucesso');
            getStock(quant, id);
        }
    }
    else
    {
        window.alert('Compra não efetuada, quantidade não é um número', quant);
    }
}

function getStock (quant, id)
{

    
}