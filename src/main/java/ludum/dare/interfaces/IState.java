package ludum.dare.interfaces;

public interface IState {
    void enter();

    void exit();

    IState update(float delta);
}
