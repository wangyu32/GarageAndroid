//package garage.wangyu.com.garageandroid.activitys;
//
//import android.os.Bundle;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import garage.wangyu.com.garageandroid.helper.PagedQueryResultHelper;
//
//public class PageListViewActivity extends BaseActivity {
//
//    private PagedQueryResultHelper pagedQueryResultHelper;
//    private Map<String, Object> params;
//    private ListView list;
//    private List<Integer> columnWidths;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        //  TODO  Auto-generated  method  stub
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.mynotifylist);
//        setOnClickListener();
//
//
//        columnWidths = new ArrayList<>();
//        int weight = this.getWindowManager().getDefaultDisplay().getWidth();
//        columnWidths.add(weight);
//
//        pagedQueryResultHelper = new PagedQueryResultHelper(this);
//        pagedQueryResultHelper.setTitleCellWidth(columnWidths);
//        //这个方法是用来查询数据并显示在LISTVIEW中的，我们这次的重点不在这里，我只列出不详讲，有兴趣的朋友可以自己研究下listview		                query();
//        protected void query () {
//            params = new HashMap<>();
//            params.put("userName", UserInfo.getUserName());
//
//            pagedQueryResultHelper.setQueryParams("getBuilderList", params, "startNum", "endNum", "MyNotifyList", R.array.build_list_name, R.array.build_list);
//            if (pagedQueryResultHelper.initialQuery() == null) {
//                new AlertDialog.Builder(this).setTitle(R.string.message_title).setMessage("没有查询到相关信息，请返回！").setPositiveButton(R.string.OK_text, null).setPositiveButton(R.string.OK_text, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        BuilderList1.this.finish();
//
//                    }
//                }).show();
//            }
//            ;
//
//            list = (ListView) findViewById(R.id.list);
//
//            list.setOnItemClickListener(new OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int index, long arg3) {
//
//                    List<Map<String, Object>> data = pagedQueryResultHelper.getWholeResult();
//                    Intent intent = new Intent(BuilderList1.this, FloorInfo.class);
//                    intent.putExtra("value", (Serializable) data.get(index));
//                    intent.putExtra("buildid", data.get(index).get("BUILDID").toString());
//                    startActivity(intent);
//                }
//            });
//        }
//
//        @Override public void onClick (View arg0){
//            switch (arg0.getId()) {
//                case R.id.first_page:
//                    pagedQueryResultHelper.firstPage();
//                    break;
//                case R.id.page_down:
//                    pagedQueryResultHelper.pageDown();
//                    break;
//                case R.id.page_up:
//                    pagedQueryResultHelper.pageUp();
//                    break;
//                case R.id.last_page:
//                    pagedQueryResultHelper.lastPage();
//                    break;
//                case R.id.to_page:
//                    pagedQueryResultHelper.toPage();
//                    break;
//            }
//        }
//
//        @Override protected void clear(){
//        }
//
//        @Override protected void setOnClickListener () {
//        }
//
//    }
//
//}
