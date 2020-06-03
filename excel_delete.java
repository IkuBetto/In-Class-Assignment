package kai;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;

public class excel_delete {

    public excel_delete(String index,String index2,String id) throws IOException {

    	int i = Integer.parseInt(index);//index
    	int t = Integer.parseInt(index2);//i
        // 変更元を取込
        FileInputStream in
            = new FileInputStream("C:\\Users\\74160\\Desktop\\pleiades-e4.2-java-jre_20130303\\pleiades\\workspace\\seminar final\\semina2.xlsx");

        Workbook book = null;
        try {
	    // 今回、WorkBookはWorkbookFactoryを使って作成します
	    book = WorkbookFactory.create(in);

	} catch (EncryptedDocumentException e1) {
	    e1.printStackTrace();

	} catch (InvalidFormatException e1) {
   	    e1.printStackTrace();
	}

	// 「サンプル」という名前のシートを取得
	Sheet sheet = book.getSheet("Sheet1");

	// 1行目取得 ※Excel上、行番号は1からスタートしてますが、
	// ソース内では0からのスタートになっているので要注意！
	Row row3 = CellUtil.getRow(t+11,sheet);

	// 1つ目のセルを取得 ※行と同じく、0からスタート
	Cell cell3 = CellUtil.getCell(row3,i);// Excel上、「A1」の場所

	boolean ret=false;

	if(id.equals(String.valueOf(cell3.getStringCellValue()))){

		ret=true;
		row3.removeCell(cell3);
	}if(ret){
		Row row = CellUtil.getRow(t+1,sheet);

		// 1つ目のセルを取得 ※行と同じく、0からスタート
		Cell cell = CellUtil.getCell(row,i);// Excel上、「A1」の場所
		row.removeCell(cell);

		Row row2 = CellUtil.getRow(t+6,sheet);

		// 1つ目のセルを取得 ※行と同じく、0からスタート
		Cell cell2 = CellUtil.getCell(row2,i);// Excel上、「A1」の場所
		row2.removeCell(cell2);



	}



	// ここから出力処理
	FileOutputStream out = null;
	try {
	    // 出力先のファイルを指定
	    out = new FileOutputStream("C:\\Users\\74160\\Desktop\\pleiades-e4.2-java-jre_20130303\\pleiades\\workspace\\seminar final\\semina2.xlsx");
	    // 上記で作成したブックを出力先に書き込み
	    book.write(out);

	} catch (FileNotFoundException e) {
	    System.out.println(e.getStackTrace());

	} finally {
	    // 最後はちゃんと閉じておきます

	    book.close();
	}
    }
}