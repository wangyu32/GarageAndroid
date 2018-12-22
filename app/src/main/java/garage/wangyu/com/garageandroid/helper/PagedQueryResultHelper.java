//package garage.wangyu.com.garageandroid.helper;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//import org.Base.Activities.QueryContextActivity;
//import org.Base.Container.DataTableAdapter;
//import org.Base.Container.myListViewTitle;
//import org.Base.Webservice.WSObjectListUtil;
//import org.Base.Webservice.WSObjectMapUtil;
//import org.Base.Webservice.WSObjectUtil;
//import org.Base.Webservice.WSUtil;
//import org.Base.Webservice.WebServiceConfig;
//import org.DigitalCM.R;
//import org.ksoap2.serialization.SoapObject;
//
//import android.app.AlertDialog;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//
//public class PagedQueryResultHelper {
//
//    private final static int initialFromRecordNum = 1;//每页从第1条开始
//    private final static int initialToRecordNum = 20;//每页可以显示20条
//
//    public PagedQueryResultHelper(QueryContextActivity contextActivity) {//构造函数用来初始化，主要是用来接收传过来的activity做为上下文对象
//        this.contextActivity = contextActivity;
//
//        findViews();//调用此方法找到传过来的activity的控件对象
//        setOnClickListener();//给控件设置监听
//        if (currentPageText != null) {//如果所有页面只有一页，那就只显示一页
//            setCurrentPage(1);
//        }
//    }
//
//    public List<List<String>> initialQuery() {
//        setRecordNumEachPage(initialToRecordNum - initialFromRecordNum + 1);//设置每页的记录条数
//        return query(initialFromRecordNum, initialToRecordNum);
//
//    }
//
//    public List<List<String>> initialQuery01() {
//        return query(methodName, params);
//    }
//
//    private void setCurrentPage(int currentPage) {//设置当前页
//        this.currentPage = currentPage;
//        currentPageText.setText(String.valueOf(currentPage));
//    }
//
//    public void setMarkable(boolean bMarkable) {//这个是用来标记记录的
//        this.bMarkable = bMarkable;
//    }
//
//    public void setRecordNumEachPage(int recordNumEachPage) {);//设置每页的记录条数
//        this.recordNumEachPage = recordNumEachPage;
//        recordNumEachPageText.setText(String.valueOf(recordNumEachPage));
//    }
//
//    public void setTotalRecord(int totalRecord) {//设置总记录数
//        this.totalRecord = totalRecord;
//        totalRecordNumText.setText(String.valueOf(totalRecord));
//        getTotalPageNumText();
//    }
//
//    private QueryContextActivity contextActivity;
//    private TextView totalPageNumText;
//    private TextView totalRecordNumText;
//    private TextView recordNumEachPageText;
//    private Button firstPage;
//    private Button pageDown;
//    private Button pageUp;
//    private Button lastPage;
//    private EditText currentPageText;
//    private Button toPage;
//
//    private int totalPage;
//    private int totalRecord;
//    private int recordNumEachPage;
//    private int currentPage;
//    private String fromRecordNumField;
//    private String toRecordNumField;
//    private String methodName;
//    private Map<String, Object> params;
//    private String contentTableName;
//    // private String countTableName;
//    private int headerNameId;
//    private int headerId;
//    private List<Integer> columnWidths = null;
//    private boolean bSetTitleValue = false;
//    private List<Map<String, Object>> wholeresult = null;
//    private List<List<String>> rows = null;
//    private boolean bMarkable = false;
//    private DataTableAdapter dataTableAdapter;
//
//    public void setQueryParams(String methodName, Map<String, Object> params,
//                               String fromRecordNumField, String toRecordNumField,
//                               String contentTableName, int headerNameId, int headerId) {
//        this.methodName = methodName;
//        this.params = params;
//        this.fromRecordNumField = fromRecordNumField;
//        this.toRecordNumField = toRecordNumField;
//        this.contentTableName = contentTableName;
//        // this.countTableName = countTableName;
//        this.headerNameId = headerNameId;
//        this.headerId = headerId;
//    }
//
//    public void setQueryParams(String methodName, Map<String, Object> params,
//                               String fromRecordNumField, String toRecordNumField,
//                               String contentTableName) {
//        this.methodName = methodName;
//        this.params = params;
//        this.fromRecordNumField = fromRecordNumField;
//        this.toRecordNumField = toRecordNumField;
//        this.contentTableName = contentTableName;
//        // this.countTableName = countTableName;
//
//    }
//
//    public void setQueryParams(String methodName, Map<String, Object> params,
//                               String contentTableName, int headerNameId, int headerId) {
//        this.methodName = methodName;
//        this.params = params;
//
//        this.contentTableName = contentTableName;
//
//        this.headerNameId = headerNameId;
//        this.headerId = headerId;
//
//    }
//
//    public void setTitleCellWidth(List<Integer> columnWidths) {
//        this.columnWidths = columnWidths;
//    }
//
//    /*
//     * public int getCurrentPage(){ return currentPage; }
//     */
//
//    private void findViews() {
//        totalPageNumText = (TextView) contextActivity
//                .findViewById(R.id.total_page_num);
//        totalRecordNumText = (TextView) contextActivity
//                .findViewById(R.id.total_record_num);
//        recordNumEachPageText = (TextView) contextActivity
//                .findViewById(R.id.record_num_each_page);
//        firstPage = (Button) contextActivity.findViewById(R.id.first_page);
//        pageDown = (Button) contextActivity.findViewById(R.id.page_down);
//        pageUp = (Button) contextActivity.findViewById(R.id.page_up);
//        lastPage = (Button) contextActivity.findViewById(R.id.last_page);
//        currentPageText = (EditText) contextActivity
//                .findViewById(R.id.current_page);
//        toPage = (Button) contextActivity.findViewById(R.id.to_page);
//    }
//
//    private void setOnClickListener() {
//        if (firstPage != null) {
//            firstPage.setOnClickListener(contextActivity);
//        }
//        if (pageDown != null) {
//            pageDown.setOnClickListener(contextActivity);
//        }
//        if (pageUp != null) {
//            pageUp.setOnClickListener(contextActivity);
//        }
//        if (lastPage != null) {
//            lastPage.setOnClickListener(contextActivity);
//        }
//        if (toPage != null) {
//            toPage.setOnClickListener(contextActivity);
//        }
//    }
//
//    private void getRecordNumEachPageText() {
//        try {
//            recordNumEachPage = Integer.parseInt(recordNumEachPageText
//                    .getText().toString());
//        } catch (NumberFormatException e) {
//            Log.e("NumberFormatException", e.getMessage());
//        }
//    }
//
//    private boolean outOfEachPageRange() {
//        recordNumEachPage = Integer.parseInt(recordNumEachPageText.getText()
//                .toString());
//        if (recordNumEachPage > 20 || recordNumEachPage < 1) {
//            return true;
//        }
//        return false;
//    }
//
//    private void getTotalPageNumText() {
//        getRecordNumEachPageText();
//        if (recordNumEachPage == 0)
//            return;
//        if (totalRecord == 0) {
//            totalPage = 0;
//            totalPageNumText.setText(String.valueOf(0));
//            return;
//        }
//        if (totalRecord % recordNumEachPage == 0) {//java中分页常用到的计算公式
//            totalPage = totalRecord / recordNumEachPage;
//        } else {
//            totalPage = totalRecord / recordNumEachPage + 1;
//        }
//        totalPageNumText.setText(String.valueOf(totalPage));
//    }
//
//    public void pageDown() {
//        if (outOfEachPageRange()) {
//            AlertDialog.Builder dialog = new AlertDialog.Builder(
//                    contextActivity);
//            dialog.setTitle("提示：").setMessage("每页显示条数在1~20之间！")
//                    .setPositiveButton("确认", null).show();
//            return;
//        }
//        if (currentPage != totalPage) {
//            setCurrentPage(++currentPage);
//            getTotalPageNumText();
//            query(recordNumEachPage * (currentPage - 1) + 1, recordNumEachPage
//                    * currentPage);
//        }
//    }
//
//    public void pageUp() {
//        if (outOfEachPageRange()) {
//            AlertDialog.Builder dialog = new AlertDialog.Builder(
//                    contextActivity);
//            dialog.setTitle("提示：").setMessage("每页显示条数在1~20之间！")
//                    .setPositiveButton("确认", null).show();
//            return;
//        }
//        if (currentPage != 1 && currentPage != 0) {
//            setCurrentPage(--currentPage);
//            getTotalPageNumText();
//            query(recordNumEachPage * (currentPage - 1) + 1, recordNumEachPage
//                    * currentPage);
//        }
//    }
//
//    public void firstPage() {
//        if (outOfEachPageRange()) {
//            AlertDialog.Builder dialog = new AlertDialog.Builder(
//                    contextActivity);
//            dialog.setTitle("提示：").setMessage("每页显示条数在1~20之间！")
//                    .setPositiveButton("确认", null).show();
//            return;
//        }
//        getTotalPageNumText();
//        if (totalPage > 1) {
//            setCurrentPage(1);
//            query(1, recordNumEachPage);
//        }
//    }
//
//    public void lastPage() {
//        if (outOfEachPageRange()) {
//            AlertDialog.Builder dialog = new AlertDialog.Builder(
//                    contextActivity);
//            dialog.setTitle("提示：").setMessage("每页显示条数在1~20之间！")
//                    .setPositiveButton("确认", null).show();
//            return;
//        }
//        getTotalPageNumText();
//        if (totalPage != 1 && totalPage != 0) {
//            setCurrentPage(totalPage);
//            query(recordNumEachPage * (totalPage - 1) + 1, totalRecord);
//        }
//    }
//
//    public void toPage() {
//        if (outOfEachPageRange()) {
//            AlertDialog.Builder dialog = new AlertDialog.Builder(
//                    contextActivity);
//            dialog.setTitle("提示：").setMessage("每页显示条数在1~20之间！")
//                    .setPositiveButton("确认", null).show();
//            return;
//        }
//        try {
//            currentPage = Integer
//                    .parseInt(currentPageText.getText().toString());
//        } catch (NumberFormatException e) {
//            Log.e("NumberFormatException", e.getMessage());
//            return;
//        }
//        getTotalPageNumText();
//        query(recordNumEachPage * (currentPage - 1) + 1, recordNumEachPage
//                * currentPage);
//        currentPageText.setText(String.valueOf(currentPage));
//    }
//
//    public List<List<String>> query(int fromRecordNum, int toRecordNum) {
//        return query(methodName, params, fromRecordNumField, toRecordNumField,
//                fromRecordNum, toRecordNum);
//    }
//
//    private List<List<String>> query(String methodName,
//                                     Map<String, Object> params, String fromRecordNumField,
//                                     String toRecordNumField, int fromRecordNum, int toRecordNum) {
//        params.put(fromRecordNumField, fromRecordNum);
//        params.put(toRecordNumField, toRecordNum);
//
//        return query(methodName, params);
//    }
//
//    public List<Map<String, Object>> getWholeResult() {
//        return wholeresult;
//    }
//
//    public List<Map<String, Object>> getResult() {
//        return wholeresult;
//    }
//
//    public List<Boolean> getCheckBoxState() {
//        return dataTableAdapter.getCheckBoxState();
//    }
//
//    public List<List<String>> query(String methodName,
//                                    Map<String, Object> params) {//此方法要重点注意，因为这里是根据webservice查询到的数据，所以要传递来方法和参数
//        SoapObject result = null;
//
//        if (methodName == null) {
//            if (bSetTitleValue == false) {//下面是实现listview的标题显示
//                bSetTitleValue = true;
//                myListViewTitle listViewTitle = new myListViewTitle(
//                        contextActivity);
//                if (this.columnWidths != null) {
//                    listViewTitle.setTitleCellWidth(this.columnWidths);
//                }
//                List<String> headerList = Arrays.asList(contextActivity
//                        .getResources().getStringArray(headerId));
//                listViewTitle.setTitleCellValue(headerList);
//            }
//            return null;
//        }
//
//        try {
//            result = WSUtil.getSoapObjectByCallingWS(//这里是android调用webservice的典型方法
//                    WebServiceConfig.NAMESPACE, methodName, params,
//                    WebServiceConfig.wsdl);
//        } catch (Exception e) {
//            new AlertDialog.Builder(contextActivity)
//                    .setTitle(R.string.message_title)
//                    .setMessage(R.string.connection_error)
//                    .setPositiveButton(R.string.OK_text, null).show();
//            Log.e("Exception", e.getMessage());
//        }
//
//        if (result == null) {//如果从webservice得到的结果为null，那我们就找到listview，并赋空值，将分页的显示值设为0
//            ListView list = (ListView) contextActivity.findViewById(R.id.list);
//            list.setAdapter(null);
//            setTotalRecord(0);
//            this.setCurrentPage(0);
//            return null;
//        }
//
//        SoapObject dataSet = WSObjectUtil.getDataSetObject(result);//如果从webservice得到的结果不为空，则转化成dataSet
//        if (dataSet == null) {//如果dataSet为空，则显示对话框，没有查询到相关数据
//            new AlertDialog.Builder(contextActivity)
//                    .setTitle(R.string.message_title).setMessage("没有查询到相关数据！")
//                    .setPositiveButton(R.string.OK_text, null);
//
//            return null;
//        }
//        // 否则，就取得列名，并将取得的dataset转化成list型的数据，最后将数据显示在listview,并通过rows返回数据
//        List<String> columnNameList = Arrays.asList(contextActivity
//                .getResources().getStringArray(headerNameId));
//        WSObjectListUtil wsObjectListUtil = new WSObjectListUtil();
//        if (contentTableName != null) {
//            rows = wsObjectListUtil.getTableValueList(dataSet,
//                    contentTableName, columnNameList);
//        } else {
//            rows = wsObjectListUtil.getTableValueList(dataSet, columnNameList);
//        }
//        if (wholeresult != null) {
//            wholeresult.clear();
//        }
//        WSObjectMapUtil wsObjectMapUtil = new WSObjectMapUtil();
//        if (contentTableName != null) {
//            wholeresult = wsObjectMapUtil.getRowMapList(contentTableName,
//                    dataSet);
//        } else {
//            wholeresult = WSObjectMapUtil.getRowMapList(dataSet);
//        }
//        SoapObject countTable = (SoapObject) dataSet.getProperty(dataSet
//                .getPropertyCount() - 1);
//        try {
//            totalRecord = Integer
//                    .parseInt(countTable.getProperty(0).toString());
//        } catch (Exception e) {
//            Log.e("Exception", e.getMessage());
//        }
//        if (totalRecord != 0 && this.totalRecord == 0
//                && currentPageText != null) {
//            this.setCurrentPage(1);
//        }
//        setTotalRecord(totalRecord);
//
//        if (bSetTitleValue == false) {
//            bSetTitleValue = true;
//            myListViewTitle listViewTitle = new myListViewTitle(contextActivity);
//            if (this.columnWidths != null) {
//                listViewTitle.setTitleCellWidth(this.columnWidths);
//            }
//            List<String> headerList = Arrays.asList(contextActivity
//                    .getResources().getStringArray(headerId));
//            listViewTitle.setTitleCellValue(headerList);
//        }
//
//        ListView list = (ListView) contextActivity.findViewById(R.id.list);
//        dataTableAdapter = new DataTableAdapter(contextActivity, rows);
//        if (bMarkable) {
//            dataTableAdapter.setMarkable(true);
//        }
//        if (this.columnWidths != null) {
//            dataTableAdapter.setColumnWidths(this.columnWidths);
//        }
//        list.setAdapter(dataTableAdapter);//注意，关键点：将数据显示在listview
//
//        return rows;//返回数据
//    }
//}
