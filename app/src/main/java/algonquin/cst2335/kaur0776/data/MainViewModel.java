package algonquin.cst2335.kaur0776.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MutableLiveData<String> editstring = new MutableLiveData<>();
    public MutableLiveData<Boolean> isselected = new MutableLiveData<>();

}

