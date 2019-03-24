package ServerConnexion;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nassim.projet_integration.R;

import java.util.List;

/**
 * Created by nassim on 28/02/2019.
 */

public class Publication_adapter extends ArrayAdapter<Publication>{

    public Publication_adapter(Context context,List<Publication> publicationsList) {
        super(context, 0,publicationsList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.raw_pub,parent,false);
        }
        PublicationViewHolder viewHolder = (PublicationViewHolder) convertView.getTag() ;
        if (viewHolder == null){
            viewHolder = new PublicationViewHolder() ;
            viewHolder.pseudo = (TextView)convertView.findViewById(R.id.pseudo);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image_publication);
            viewHolder.titre = (TextView)convertView.findViewById(R.id.titre_id);
            viewHolder.date_pub = (TextView)convertView.findViewById(R.id.date_publication_id);
            convertView.setTag(viewHolder);
        }
        // getItem(posision) va récupérer l'item de la liste<publication>
        Publication publication = getItem(position);

        viewHolder.titre.setText(publication.getTitre());
        viewHolder.pseudo.setText(publication.getPseudo());
        viewHolder.date_pub.setText(publication.getDate_pub());
        viewHolder.image.setImageBitmap(publication.image);
        return convertView ;
    }
}