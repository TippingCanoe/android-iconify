/**
 * Copyright 2013 Joan Zapata
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * It uses FontAwesome font, licensed under OFL 1.1, which is compatible
 * with this library's license.
 *
 *     http://scripts.sil.org/cms/scripts/render_download.php?format=file&media_id=OFL_plaintext&filename=OFL.txt
 */
package com.joanzapata.android.iconify;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spanned;
import android.widget.TextView;

import static android.text.Html.fromHtml;
import static android.text.Html.toHtml;
import static com.joanzapata.android.iconify.Utils.replaceIcons;
import static java.lang.String.valueOf;

public final class Iconify {

    private static final String TTF_FILE = "hukd.ttf";

    public static final String TAG = Iconify.class.getSimpleName();

    private static Typeface typeface = null;

    private Iconify() {
        // Prevent instantiation
    }

    /**
     * Transform the given TextViews replacing {icon_xxx} texts with icons.
     */
    public static final void addIcons(TextView... textViews) {
        for (TextView textView : textViews) {
            textView.setTypeface(getTypeface(textView.getContext()));
            textView.setText(compute(textView.getText()));
        }
    }

    public static CharSequence compute(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            String text = toHtml((Spanned) charSequence);
            return fromHtml(replaceIcons(new StringBuilder((text))).toString());
        }
        String text = charSequence.toString();
        return replaceIcons(new StringBuilder(text));
    }

    public static final void setIcon(TextView textView, IconValue value) {
        textView.setTypeface(getTypeface(textView.getContext()));
        textView.setText(valueOf(value.character));
    }

    /**
     * The typeface that contains FontAwesome icons.
     *
     * @return the typeface, or null if something goes wrong.
     */
    public static final Typeface getTypeface(Context context) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), TTF_FILE);
        }
        return typeface;
    }

    public static enum IconValue {

        icon_meta_comment('1'),     // 0x31     '1'
        icon_report('t'),           // 0x74     't'
        icon_quote('i'),            // 0x69     'i'
        icon_expire(';'),           // 0x3b     ';'
        icon_mode_text('n'),        // 0x6e     'n'
        icon_mode_grid('m'),        // 0x6d     'm'
        icon_mode_list('N'),        // 0x4e     'N'
        icon_link('='),             // 0x3d     '='
        icon_moreactions('v'),      // 0x76     'v'
        icon_edit('*'),             // 0x2a     '*'
        icon_clear(']'),            // 0x5d     ']'
        icon_arrow_up('w'),         // 0x77     'w'
        icon_arrow_down('W'),       // 0x57     'W'
        icon_app_ios('@'),          // 0x40     '@'
        icon_app_android('!'),      // 0x21     '!'
        icon_subscribe('y'),        // 0x79     'y'
        icon_unsubscribe('Y'),      // 0x59     'Y'
        icon_gotodeal('j'),         // 0x6a     'j'
        icon_counts_share('p'),     // 0x70     'p'
        icon_counts_comment('o'),   // 0x6f     'o'
        icon_meta_hot('3'),         // 0x33     '3'
        icon_meta_posted('2'),      // 0x32     '2'
        vote_hot('a'),              // 0x61     'a'
        vote_cold('b'),             // 0x62     'b'
        icon_arrow_forward('Q'),    // 0x51     'Q'
        icon_reply('u'),            // 0x75     'u'
        icon_like('h'),             // 0x68     'h'
        icon_vouchercode('q'),      // 0x71     'q'
        icon_top_submit('c'),       // 0x63     'c'
        icon_top_cancel('d'),       // 0x64     'd'
        icon_top_back_ios('e'),     // 0x65     'e'
        icon_top_back_android('E'), // 0x45     'E'
        icon_smiley('r'),           // 0x72     'r'
        icon_settings('f'),         // 0x66     'f'
        icon_photo('z'),            // 0x7a     'z'
        icon_newmessage('x'),       // 0x78     'x'
        icon_menu_search('4'),      // 0x34     '4'
        icon_menu_profile('5'),     // 0x35     '5'
        icon_menu_pm('6'),          // 0x36     '6'
        icon_menu_hukd('7'),        // 0x37     '7'
        icon_menu_activity('8'),    // 0x38     '8'
        icon_login_twitter('9'),    // 0x39     '9'
        icon_login_facebook('0'),   // 0x30     '0'
        icon_login_email('-'),      // 0x2d     '-'
        icon_format_under(','),     // 0x2c     ','
        icon_format_italic('.'),    // 0x2e     '.'
        icon_format_bold('/'),      // 0x2e     '/'
        icon_delete('['),           // 0x5b     '['
        icon_checkmark('\\'),       // 0x5c     '\'
        icon_activity_relto('R'),   // 0x52     'R'
        icon_activity_follow('T'),  // 0x54     'T'
        icon_activity_deala('U'),   // 0x55     'U'
        icon_camera('%'),           // 0x25     '%'
        icon_info('$'),             // 0x24     '$'
        icon_deal('#');             // 0x23     '#'

        char character;

        IconValue(char character) {
            this.character = character;
        }

        public String formattedName() {
            return "{" + name() + "}";
        }

        public char character() {
            return character;
        }
    }
}
