package Menu;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import Tools.G;
import Tools.HelperJSON;
import WebService.Interface.GetRestResult;
import WebService.RestWebServiceGet;

/**
 * Created by me on 12/15/2016.
 */

public class RightMenu {
    private Context _context;
    private RecyclerView _rightMenu;

    public RightMenu(Context context, RecyclerView rightMenu) {
        this._context = context;
        this._rightMenu = rightMenu;
        setData(G.urlRestCategories);
    }

    private void setData(String url) {
        new RestWebServiceGet(_context, new GetRestResult() {
            @Override
            public void getData(String data) {
                setAdapter(data);
            }
        }).execute(url);
    }

    private void setAdapter(String data) {
        if (data != null) {
            List<HashMap<String, String>> tmp = HelperJSON.parseJson(data);
            RecyclerAdapterRightMenu adapterRightMenu = new RecyclerAdapterRightMenu(this._context, tmp);
            this._rightMenu.setLayoutManager(new LinearLayoutManager(this._context));
            this._rightMenu.setAdapter(adapterRightMenu);
        }
    }
}
