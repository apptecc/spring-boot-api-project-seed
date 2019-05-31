package cn.nxnil.yonghui.center.ordercenter;

import com.google.common.base.Splitter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 生成 mini 店自动补货的sql 语句
 */
public class MiniShopAutoCommitGenerator {

    private static final String AUTO_CALC_SHOP_CODES = "9ND0,9NH9,9NI8,9NJ2,9NH8";
    private static final String AUTO_COMMIT_SHOP_CODES = "9ND0,9NH9,9NI8,9NJ2,9NH8";

    private static final String AUTO_COMMIT_TEMPLATE = "INSERT INTO shop_order_type_conf (order_type, purchase_org, purchase_org_name, prefer_dc_code, big_category_code, big_category_name, small_category_code, small_category_name, product_code, product_barcode, product_name, debar_flag, pr_addition_type, status, created_by, updated_by, piece_flag, root_category_code, root_category_name, mid_category_code, mid_category_name, shop_type, shop_type_name) VALUES ('AUTO_COMMIT', '', '', '%s', '', '', '', '', '', '', '', 0, '', 1, 'sys', 'sys', 1, '', '', '', '', '', '');\n";
    private static final String AUTO_CALC_TEMPLATE = "INSERT INTO shop_order_type_conf_calc (order_type, purchase_org, purchase_org_name, prefer_dc_code, big_category_code, big_category_name, small_category_code, small_category_name, product_code, product_barcode, product_name, debar_flag, pr_addition_type, status, created_by, updated_by, piece_flag, root_category_code, root_category_name, mid_category_code, mid_category_name, shop_type, shop_type_name) VALUES ('AUTO_COMMIT', '', '', '%s', '', '', '', '', '', '', '', 0, '', 1, 'sys', 'sys', 1, '', '', '', '', '', '');\n";


    public static void main(String[] args) throws Exception {
        List<String> autoCalcList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(AUTO_CALC_SHOP_CODES);
        List<String> autoCommitList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(AUTO_COMMIT_SHOP_CODES);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());
        String fileName = "miniShopAutoCommit" + today + ".txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/Users/lys/Desktop/yh-document/routin/" + fileName)));

        for (String s : autoCalcList) {
            bw.write(String.format(AUTO_CALC_TEMPLATE, s));
        }

        bw.newLine();

        for (String s : autoCommitList) {
            bw.write(String.format(AUTO_COMMIT_TEMPLATE, s));
        }

        bw.flush();
        bw.close();
    }

}
