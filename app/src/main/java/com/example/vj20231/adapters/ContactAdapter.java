package com.example.vj20231.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vj20231.InfoContact;
import com.example.vj20231.R;
import com.example.vj20231.entities.Contact;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.NameViewHolder> {

    private List<Contact> items;

    public ContactAdapter(List<Contact> items) {
        this.items = items;
    }

    public void setContacts(List<Contact> contacts) {
        this.items = contacts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        NameViewHolder viewHolder = new NameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        Contact item = items.get(position);
        View view = holder.itemView;

        ImageView ivContactImage = view.findViewById(R.id.ivContactImage);
        TextView tvNombre = view.findViewById(R.id.tvNombre);
        Button btnVer = view.findViewById(R.id.btnVer);
        Button btnComentar = view.findViewById(R.id.btnComentar);
        EditText etComentario = view.findViewById(R.id.etComentario);
        TextView tvComentario = view.findViewById(R.id.tvComentario);

        // Load the image using Picasso
        Picasso.get().load(item.getImgContact()).into(ivContactImage);

        tvNombre.setText(item.getNameContact());

        btnComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comentario = etComentario.getText().toString();
                tvComentario.setText(comentario);
                etComentario.setText("");
            }
        });

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition(); // Obtener la posición dinámicamente
                Contact clickedItem = items.get(clickedPosition); // Obtener el elemento correspondiente
                Intent intent = new Intent(v.getContext(), InfoContact.class);
                intent.putExtra("id", clickedItem.getId()); // Pasar el ID del elemento clicado
                intent.putExtra("nombre", clickedItem.getNameContact());
                intent.putExtra("imagen", clickedItem.getImgContact()); // Pasar el nuevo atributo (imagen)
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addContact(Contact contact) {
        items.add(contact);
        notifyDataSetChanged();
    }

    public static class NameViewHolder extends RecyclerView.ViewHolder {
        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

