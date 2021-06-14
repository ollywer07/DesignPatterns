package interfaces;


public abstract class PizzaDecorator implements PizzaComponent {
    public void setDecorated(PizzaComponent decorated) {
        this.decorated = decorated;        
    }
    protected PizzaComponent decorated;
}
