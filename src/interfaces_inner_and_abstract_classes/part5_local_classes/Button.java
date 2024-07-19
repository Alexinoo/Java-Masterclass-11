package interfaces_inner_and_abstract_classes.part5_local_classes;

/*
 * Button Class
 *
 * Fields
 *  title : String
 *  onClickListener : OnClickListener (interface)
 *
 * Constructor
 *  Button(String title)
 *
 * Getter & Setter
 *  getTitle : String
 *  setOnClickListener(OnClickListener onClickListener) : void
 *
 * Methods
 *  onClick : void
 *  - handle the click
 */


public class Button {
    private String title;
    private OnClickListener onClickListener;

    public Button(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void onClick(){
        this.onClickListener.handleOnClick(this.title);
    }

    public interface OnClickListener {
        void handleOnClick(String title);
    }
}
