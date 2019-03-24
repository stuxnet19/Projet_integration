package ServerConnexion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nassim.projet_integration.R;


import org.w3c.dom.Text;

import java.util.List;

public class Comment_adapter extends ArrayAdapter<Comment> {

    public Comment_adapter(Context context,List<Comment> commentList) {
        super(context, 0,commentList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.raw_comment,parent,false);
        }

        CommentViewHolder viewHolder =  (CommentViewHolder) convertView.getTag();

        if (viewHolder == null){
            viewHolder = new CommentViewHolder() ;
            viewHolder.pseudo_commentaire = (TextView)convertView.findViewById(R.id.pseudo_commentaire);
            viewHolder.date_commenatire = (TextView)convertView.findViewById(R.id.date_commentaire);
            viewHolder.text_commenatire = (TextView)convertView.findViewById(R.id.text_commentaire);
            convertView.setTag(viewHolder);
        }
        // getItem(posision) va récupérer l'item de la liste<publication>
        Comment commentaire = getItem(position);

        viewHolder.text_commenatire.setText(commentaire.getText_commenatire());
        viewHolder.date_commenatire.setText(commentaire.getDate_commenatire());
        viewHolder.pseudo_commentaire.setText(commentaire.getPseudo_commentaire());

        return convertView ;
    }
}
