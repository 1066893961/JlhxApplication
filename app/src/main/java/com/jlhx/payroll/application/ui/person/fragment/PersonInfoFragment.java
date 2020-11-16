package com.jlhx.payroll.application.ui.person.fragment;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jlhx.payroll.application.R;
import com.jlhx.payroll.application.base.BaseFragment;
import com.jlhx.payroll.application.utils.FileUtils;
import com.jlhx.payroll.application.utils.ToastUtils;
import com.jlhx.payroll.application.views.ActionSheetView;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static com.jlhx.payroll.application.JlhxApplication.getApplication;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:
 */

public class PersonInfoFragment extends BaseFragment {

    @BindView(R.id.make_photo_tv)
    TextView makePhotoTv;
    @BindView(R.id.header_iv)
    ImageView mHeaderIv;

    // 头像文件
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    private static final String CROP_IMAGE_FILE_NAME = "bala_crop.jpg";
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;
    private final int PERMISSION_READ_AND_CAMERA = 0;//读和相机权限
    private final int PERMISSION_READ = 1;//读取权限
    private String mExtStorDir;
    private Uri mUriPath;


    public static PersonInfoFragment newInstance() {
        PersonInfoFragment fragment = new PersonInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person_info;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mExtStorDir = Environment.getExternalStorageDirectory().toString();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            ToastUtils.showShortToast(R.string.cancel);
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (FileUtils.hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
//                    cropRawPhoto(Uri.fromFile(tempFile));
                    cropRawPhoto(getImageContentUri(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                /*if (intent != null) {
                    setImageToHeadView(intent);    //此代码在小米有异常，换以下代码
                }*/
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(mUriPath));
                    setImageToHeadView(intent, bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }


    @OnClick(R.id.make_photo_tv)
    public void onViewClicked() {
        showSheet();
    }

    private void showSheet() {
        ActionSheetView.getInstance(getContext(), "拍照", "从相册中获取", "取消").showSheet(new ActionSheetView.OnActionSheetSelected() {
            @Override
            public void onClick(int whichButton) {
                switch (whichButton) {
                    case ActionSheetView.CODE_BUTTON_TOP:
                        checkReadPermission();
                        break;
                    case ActionSheetView.CODE_BUTTON_CONTENT:
                        checkStoragePermission();//检查是否有权限
                        break;
                }
            }
        }, null);
    }

    /**
     * 检测读取权限
     */
    private void checkReadPermission() {
        int permission = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission == PackageManager.PERMISSION_DENIED) {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(getActivity(), permissions, PERMISSION_READ);
        } else {
            choseHeadImageFromGallery();
        }
    }

    /**
     * 检测存储权限
     */
    private void checkStoragePermission() {
        int result = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_DENIED) {
            String[] permissions = {/*Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ,*/Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(getActivity(), permissions, PERMISSION_READ_AND_CAMERA);
        } else {
            choseHeadImageFromCameraCapture();
        }
    }

    /**
     * 从本地相册选取图片作为头像
     */
    private void choseHeadImageFromGallery() {
        // 设置文件类型    （在华为手机中不能获取图片，要替换代码）
        /*Intent intentFromGallery = new Intent();
        intentFromGallery.setType("image*//*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);*/

        Intent intentFromGallery = new Intent(Intent.ACTION_PICK, null);
        intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    /**
     * 启动手机相机拍摄照片作为头像
     */
    private void choseHeadImageFromCameraCapture() {
        String savePath = mExtStorDir;

        Intent intent = null;
        // 判断存储卡是否可以用，可用进行存储

        if (FileUtils.hasSdcard()) {
            //设定拍照存放到自己指定的目录,可以先建好
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            Uri pictureUri;
            File pictureFile = new File(savePath, IMAGE_FILE_NAME);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                pictureUri = FileProvider.getUriForFile(getContext(), getActivity().getPackageName() + ".fileProvider", pictureFile);
    /*ContentValues contentValues = new ContentValues(1);
    contentValues.put(MediaStore.Images.Media.DATA, pictureFile.getAbsolutePath());
    pictureUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);*/
            } else {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pictureUri = Uri.fromFile(pictureFile);
            }
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, pictureFile.getAbsolutePath());
                pictureUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            } else {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                pictureUri = Uri.fromFile(pictureFile);
            }*/
            if (intent != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        pictureUri);
                startActivityForResult(intent, CODE_CAMERA_REQUEST);
            }
        }
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例  圆形裁剪
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        //startActivityForResult(intent, CODE_RESULT_REQUEST); //直接调用此代码在小米手机有异常，换以下代码
        String mLinshi = System.currentTimeMillis() + CROP_IMAGE_FILE_NAME;
        File mFile = new File(mExtStorDir, mLinshi);
//        mHeadCachePath = mHeadCacheFile.getAbsolutePath();

        mUriPath = Uri.parse("file://" + mFile.getAbsolutePath());
        //将裁剪好的图输出到所建文件中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUriPath);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //注意：此处应设置return-data为false，如果设置为true，是直接返回bitmap格式的数据，耗费内存。设置为false，然后，设置裁剪完之后保存的路径，即：intent.putExtra(MediaStore.EXTRA_OUTPUT, uriPath);
//        intent.putExtra("return-data", true);
        intent.putExtra("return-data", false);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 获取图片url
     *
     * @param imageFile
     * @return
     */
    public Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getActivity().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return getActivity().getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent, Bitmap b) {
        try {
            if (intent != null) {
//                Bitmap bitmap = imageZoom(b);//看个人需求，可以不压缩
                mHeaderIv.setImageBitmap(b);
//                long millis = System.currentTimeMillis();
                /*File file = FileUtil.saveFile(mExtStorDir, millis+CROP_IMAGE_FILE_NAME, bitmap);
                if (file!=null){
                    //传递新的头像信息给我的界面
                    Intent ii = new Intent();
                    setResult(new_icon,ii);
                    Glide.with(this).load(file).apply(RequestOptions.circleCropTransform())
//                                .apply(RequestOptions.fitCenterTransform())
                            .apply(RequestOptions.placeholderOf(R.mipmap.user_logo)).apply(RequestOptions.errorOf(R.mipmap.user_logo))
                            .into(mIvTouxiangPersonal);
//                uploadImg(mExtStorDir,millis+CROP_IMAGE_FILE_NAME);
                    uploadImg(mExtStorDir,millis+CROP_IMAGE_FILE_NAME);
                }*/

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
